#!/usr/bin/env bash
#
# Прогон тестов через Gradle.
#
# ПОЧЕМУ ЭТОТ СКРИПТ СУЩЕСТВУЕТ
# -----------------------------
# В этой системе корень `/` смонтирован read-only, поэтому ~/.gradle недоступен
# для записи. Обычный `./gradlew test` падает на создании lock-файла:
#
#   java.io.FileNotFoundException: ~/.gradle/wrapper/dists/.../gradle-9.3.1-bin.zip.lck
#   (Файловая система доступна только для чтения)
#
# Записываемый каталог ровно один — эта папка проекта. Поэтому:
#   1. GRADLE_USER_HOME указывает внутрь проекта (.gradle-home/);
#   2. кэш зависимостей (modules-2) один раз скопирован туда, потому что
#      Gradle пишет в него lock-файлы, и симлинк на read-only каталог не годится;
#   3. сам дистрибутив Gradle берётся из read-only ~/.gradle — он уже распакован,
#      копировать 461 МБ в проект незачем.
#
# Флаг --offline обязателен: сеть недоступна, все зависимости уже в кэше.
#
# Использование:
#   ./test.sh                     — все тесты
#   ./test.sh :3sum:test          — конкретный модуль
#   ./test.sh :3sum:test --rerun  — принудительно перезапустить
#   ./test.sh --info :3sum:test   — подробный вывод
#
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# --- находим распакованный Gradle в read-only ~/.gradle ------------------------
find_gradle_bin() {
  local candidate
  candidate="$(find "$HOME/.gradle/wrapper/dists" -maxdepth 6 -type f \
    -path "*/gradle-*/bin/gradle" 2>/dev/null | sort -V | tail -1)"
  if [[ -z "$candidate" ]]; then
    echo "ОШИБКА: не найден распакованный Gradle в ~/.gradle/wrapper/dists" >&2
    echo "Ожидался путь вида: ~/.gradle/wrapper/dists/gradle-9.3.1-bin/<hash>/gradle-9.3.1/bin/gradle" >&2
    exit 2
  fi
  echo "$candidate"
}

GRADLE_BIN="$(find_gradle_bin)"

# --- готовим GRADLE_USER_HOME внутри проекта -----------------------------------
export GRADLE_USER_HOME="$ROOT/.gradle-home"
mkdir -p "$GRADLE_USER_HOME/caches"

# Кэш зависимостей: если пусто — копируем из read-only ~/.gradle.
# Копия нужна, потому что Gradle пишет lock-файлы рядом с кэшем.
if [[ ! -d "$GRADLE_USER_HOME/caches/modules-2/files-2.1" ]]; then
  echo "Инициализация кэша зависимостей (один раз, ~80 МБ)..."
  if [[ -d "$HOME/.gradle/caches/modules-2" ]]; then
    cp -r "$HOME/.gradle/caches/modules-2" "$GRADLE_USER_HOME/caches/modules-2"
    echo "Готово."
  else
    echo "ПРЕДУПРЕЖДЕНИЕ: ~/.gradle/caches/modules-2 не найден." >&2
    echo "Без сети сборка не пройдёт." >&2
  fi
fi

# --- запуск --------------------------------------------------------------------
if [[ $# -eq 0 ]]; then
  set -- test
fi

exec "$GRADLE_BIN" "$@" --offline
