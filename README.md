# 📚 Android Book Service

**Android Book Service** — Android-приложение на **Kotlin + Jetpack Compose**
с авторизацией и CRUD-управлением книг через **PocketBase backend**.

---

## ✨ Возможности приложения

* 🔐 Авторизация пользователя
* 📚 Просмотр списка книг
* ➕ Создание новой книги
* ✏️ Редактирование существующей
* ❌ Удаление книги
* 🗂 Получение категорий
* 💾 Локальное хранение токена (через сессию)
* ⚡ Jetpack Compose UI с ViewModel и state

---

## 🧱 Архитектура

Приложение основано на **Clean Architecture с разделением на слои**:

### 🎨 Presentation (UI)

* Jetpack Compose
* Material 3 дизайн
* Navigation Compose
* ViewModel
* ResultState + выбранные элементы (SelectedItem)

### 🧠 Domain (ядро логики)

* Бизнес-модели: `Book`, `Category`, `AppSession`, `LoginResponse`
* Репозиторные интерфейсы
* Use Cases:

  * `GetAllBookUseCase`
  * `CreateBookUseCase`
  * `LoginUseCase`
  * `UpdateBookUseCase`
  * и другие

### 🌐 Data (инфраструктура)

* Retrofit + OkHttp клиент
* DTO + мапперы
* Token Interceptor
* Hilt-модули зависимостей
* Repository implementation

---

## 🌍 Backend — PocketBase

Проект включает готовую схему базы PocketBase.

📁 `dataexport/pb_schema.json`

### Как поднять backend

#### 1️⃣ Установи PocketBase

[https://pocketbase.io/docs/](https://pocketbase.io/docs/)

```bash
curl -fsSL https://pocketbase.io/install.sh | sh
```

#### 2️⃣ Запусти сервер

```bash
./pocketbase serve
```

#### 3️⃣ Импортируй схему

В админке PocketBase:

```
Settings → Import collections → pb_schema.json
```

После импорта автоматически доступны коллекции:

* books
* categories
* users / auth

#### 4️⃣ Готово 🎉

API по умолчанию раздаётся на:

```
http://127.0.0.1:8090
```

URL при необходимости меняется в `ApiFactory.kt`.

---

## ⚙️ Используемые технологии

### 🖥 Android

* Kotlin
* Jetpack Compose
* Material Design 3
* Navigation Compose
* Lifecycle ViewModel
* LiveData (локально)

### 🔌 Networking

* Retrofit 2
* Gson Converter
* OkHttp Client
* Logging Interceptor

### 🧩 Dependency Injection

* Hilt (Dagger)
* Navigation Compose with Hilt

### 🔨 Build

* Gradle (Kotlin DSL)
* Version Catalog (`libs.versions.toml`)
* Java/Kotlin target: 11

---

## 📦 Зависимости (из `build.gradle.kts`)

**Основные:**

* `com.squareup.retrofit2:retrofit`
* `com.squareup.retrofit2:converter-gson`
* `com.squareup.okhttp3:okhttp`
* `com.squareup.okhttp3:logging-interceptor`
* `com.google.dagger:hilt-android`
* `androidx.hilt:hilt-navigation-compose`
* `androidx.compose.*`
* Palette (анализ изображений, опционально)
* JUnit / Espresso

---

## 🚀 Запуск приложения

### 1️⃣ Клонировать репозиторий

```bash
git clone https://github.com/artemch5a1/android_book_service.git
```

### 2️⃣ Открыть в Android Studio (2023+)

### 3️⃣ Убедиться, что PocketBase запущен

### 4️⃣ Запустить на устройстве или эмуляторе

---

## 🧱 Структура проекта

```
app/
 ├─ data/       # API, DTO, мапперы, репозитории
 ├─ domain/     # сущности, интерфейсы, use cases
 └─ presentation/
     ├─ component
     ├─ screen
     ├─ viewmodel
     └─ navigation
```

---

## 🧪 Тестирование

📌 Unit-тесты

```
app/src/test
```

📌 Интеграционные тесты

```
app/src/androidTest
```

---

## 👤 Автор

**Артем**
GitHub: [https://github.com/artemch5a1](https://github.com/artemch5a1)

---

### 🎁 Возможное дополнение

Можно добавить:

* README на английском
* ER-диаграмму PocketBase
* Docker-образ PocketBase
* Деплой на VPS
* CI/CD и сборку релиза APK
