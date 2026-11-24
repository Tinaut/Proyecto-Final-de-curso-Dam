# 🛍️ Proyecto Final de Curso: App de compraventa

Aplicación **Android** de compraventa de artículos entre particulares, inspirada en **Wallapop**. Desarrollada en **Kotlin** como **proyecto de final de curso del ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM)**.

Los usuarios pueden registrarse, publicar anuncios con fotos y ubicación en el mapa, buscar artículos por categoría, guardar favoritos, comentar, chatear con los vendedores y recibir notificaciones.

> Proyecto educativo. No está afiliado a Wallapop.

---

## 📋 Índice

- [Funcionalidades](#-funcionalidades)
- [Pantallas de la aplicación](#-pantallas-de-la-aplicación)
- [Tecnologías](#-tecnologías)
- [Permisos de la aplicación](#-permisos-de-la-aplicación)
- [Requisitos](#-requisitos)
- [Instalación y configuración](#-instalación-y-configuración)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Seguridad](#-seguridad)
- [Autor](#-autor)

---

## ✨ Funcionalidades

- **Cuentas de usuario**
  - Registro e inicio de sesión con correo electrónico.
  - Recuperación y cambio de contraseña.
  - Edición del perfil.
  - Eliminación de la cuenta.
- **Anuncios**
  - Creación de anuncios con varias imágenes, categoría y condición del artículo.
  - Hacer fotos con la cámara para los anuncios.
  - Selección de la ubicación del anuncio con **Google Maps**.
  - Galería de imágenes en el detalle del anuncio, con visor a pantalla completa.
  - Eliminación de imágenes al crear o editar un anuncio.
  - Marcar un anuncio como vendido.
  - Listado de mis anuncios publicados.
- **Exploración**
  - Pantalla de inicio con anuncios y categorías.
  - Anuncios favoritos.
  - Detalle del vendedor.
- **Interacción entre usuarios**
  - Comentarios en los anuncios.
  - Chat entre comprador y vendedor.
  - Contacto con el vendedor por llamada o SMS.
- **Notificaciones push** mediante Firebase Cloud Messaging.

---

## 📱 Pantallas de la aplicación

### Activities

| Pantalla | Descripción |
|---|---|
| `OpcionesLogin` | Pantalla de entrada: opciones para iniciar sesión o registrarse |
| `Login_email` | Inicio de sesión con correo y contraseña |
| `Registro_email` | Registro de un nuevo usuario con correo |
| `RecuperarPassword` | Recuperación de la contraseña |
| `MainActivity` | Pantalla principal que contiene los fragments de la app |
| `CrearAnuncio` | Publicación de un nuevo anuncio |
| `SeleccionarUbicacion` | Elección de la ubicación en el mapa |
| `DetalleAnuncio` | Información completa de un anuncio |
| `DetalleVendedor` | Perfil y datos del vendedor |
| `Comentarios` | Comentarios de un anuncio |
| `ChatActivity` | Conversación entre dos usuarios |
| `EditarPerfil` | Modificación de los datos del perfil |
| `CambiarPassword` | Cambio de contraseña |
| `Eliminar_cuenta` | Eliminación de la cuenta del usuario |

### Fragments

| Fragment | Descripción |
|---|---|
| `FragmentInicio` | Inicio: anuncios y categorías |
| `FragmentChats` | Lista de conversaciones |
| `FragmentMisAnuncios` | Mis anuncios |
| `FragmentMisAnunciosPublicados` | Anuncios que he publicado |
| `FragmentFavAnuncios` | Anuncios guardados como favoritos |
| `FragmentCuenta` | Cuenta y ajustes del usuario |

### Diálogos

| Diálogo | Descripción |
|---|---|
| Agregar comentario | Escribir un comentario en un anuncio |
| Eliminar imagen | Confirmar el borrado de una imagen |
| Marcar vendido | Confirmar que el artículo se ha vendido |
| Visualizador de imagen | Ver una imagen del anuncio en grande |

---

## 🛠️ Tecnologías

- **Plataforma:** Android (Android Studio)
- **Lenguaje:** Kotlin
- **Autenticación:** Firebase Authentication
- **Bases de datos:** Firebase Realtime Database y Cloud Firestore
- **Almacenamiento de imágenes:** Firebase Storage
- **Notificaciones push:** Firebase Cloud Messaging (FCM)
- **Mapas y ubicación:** Google Maps SDK

---

## 🔐 Permisos de la aplicación

| Permiso | Para qué se usa |
|---|---|
| `INTERNET` | Comunicación con Firebase y los servicios de Google |
| `CAMERA` | Hacer fotos para los anuncios (opcional: la app funciona sin cámara) |
| `WRITE_EXTERNAL_STORAGE` | Guardar imágenes en el dispositivo |
| `ACCESS_FINE_LOCATION` / `ACCESS_COARSE_LOCATION` | Ubicación del usuario y del anuncio |
| `CALL_PHONE` | Llamar al vendedor (opcional: la app funciona sin telefonía) |
| `SEND_SMS` | Enviar un SMS al vendedor |
| `POST_NOTIFICATIONS` | Mostrar notificaciones push |

---

## ✅ Requisitos

- [Android Studio](https://developer.android.com/studio) (versión reciente)
- JDK compatible con la versión de Gradle del proyecto
- Un dispositivo Android o emulador con Google Play Services
- Un proyecto de **Firebase** propio
- Una **clave de API de Google Maps** propia

---

## 🚀 Instalación y configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/Tinaut/Proyecto-Final-de-curso-Dam.git
cd Proyecto-Final-de-curso-Dam
```

### 2. Abrir el proyecto

Abre la carpeta con Android Studio y espera a que Gradle sincronice las dependencias.

### 3. Configurar Firebase

1. Crea un proyecto en la [consola de Firebase](https://console.firebase.google.com/).
2. Añade una aplicación Android con el **nombre de paquete** de este proyecto.
3. Activa en Firebase los servicios que usa la app:
   - **Authentication** (proveedor de correo y contraseña)
   - **Realtime Database**
   - **Cloud Firestore**
   - **Storage**
   - **Cloud Messaging**
4. Descarga el archivo `google-services.json` y colócalo en la carpeta `app/`.

### 4. Configurar la clave de Google Maps

1. Crea una clave de API en [Google Cloud Console](https://console.cloud.google.com/) con el SDK de Maps para Android habilitado.
2. Añádela como recurso de texto con el nombre `mi_google_maps_apikey` (el `AndroidManifest.xml` la lee desde `@string/mi_google_maps_apikey`):

```xml
<!-- app/src/main/res/values/strings.xml -->
<string name="mi_google_maps_apikey">TU_CLAVE_DE_API</string>
```

### 5. Ejecutar

Selecciona un dispositivo o emulador y pulsa **Run ▶** en Android Studio.

---

## 📁 Estructura del proyecto

Organización de las pantallas (paquetes principales):

```
app/src/main/java/.../
├── Anuncios/            # Creación de anuncios (CrearAnuncio)
├── Chat/                # Chat entre usuarios (ChatActivity)
├── DetalleAnuncio/      # Detalle de un anuncio
├── DetalleVendedor/     # Detalle del vendedor
├── Notificaciones/      # Servicio de notificaciones push (MyFcmService)
├── Opciones_login/      # Inicio de sesión (Login_email)
├── MainActivity         # Pantalla principal
├── OpcionesLogin        # Pantalla de entrada
├── Registro_email       # Registro con correo
├── RecuperarPassword    # Recuperar contraseña
├── CambiarPassword      # Cambiar contraseña
├── EditarPerfil         # Editar perfil
├── Eliminar_cuenta      # Eliminar cuenta
├── Comentarios          # Comentarios de los anuncios
└── SeleccionarUbicacion # Selección de ubicación en el mapa
```

Los diseños XML están en `app/src/main/res/layout/`:

- `activity_*.xml`: pantallas (activities).
- `fragment_*.xml`: fragments de la pantalla principal.
- `cuadro_d_*.xml`: diálogos.
- `item_*.xml`: elementos de las listas (anuncios, categorías, chats, comentarios, imágenes...).

---

## 🔒 Seguridad

**No subas al repositorio información privada.** Añade esto a tu `.gitignore` si no lo tienes:

```
google-services.json
local.properties
*.jks
*.keystore
```

- Si publicaste alguna vez una clave de API de Google Maps o un `google-services.json` en un repositorio público, **restringe o regenera la clave** desde Google Cloud Console.
- Restringe tu clave de Maps al nombre de paquete y a la huella SHA-1 de la app.
- Revisa las **reglas de seguridad** de Realtime Database y Firestore para que solo accedan los usuarios autorizados.

---

## 👤 Autor

**Jorge Tinaut Vazquez**
Proyecto de final de curso, ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM).
GitHub: [Tinaut](https://github.com/Tinaut) / [JorgeTV-daw](https://github.com/JorgeTV-daw)