AdeTravel Android Client (Java)
=================================
What this ZIP contains:
- Android Studio project skeleton (app module) designed to work with your Django backend client endpoints.
- Activities/Fragments: Login, Register, Main (Destinos, Paquetes), Package detail, Reservation, My reservations.
- Networking: Retrofit + Gson. Change BASE_URL in ApiConstants.java to point to your backend.

Required backend API endpoints (examples - adapt to your backend):
- POST /api/auth/login/  {email, password} -> {token, user_id, email}
- POST /api/auth/register/ {name, email, password} -> {token? or success}
- GET  /api/destinos/ -> list of destinos (id, nombre, descripcion, imagen)
- GET  /api/paquetes/ -> list of paquetes (id, titulo, resumen, imagen, precio)
- GET  /api/paquetes/{id}/ -> package detail (id, titulo, descripcion, imagen, precio, duracion)
- POST /api/reservas/ -> create reservation (Authorization: Token <token>) body: paquete, cliente_nombre, pax, fecha
- GET  /api/mis_reservas/ -> list reservations for authenticated user (Authorization header required)

How to use:
1. Open this folder in Android Studio (File > Open > select build.gradle or folder).
2. In app/src/main/java/com/adetravel/api/ApiConstants.java set BASE_URL to your backend (for emulator use http://10.0.2.2:8000/).
3. Build & Run. The app will attempt to call the endpoints above.

Notes & next steps I can do for you (optional):
- I can add image loading previews using Glide (already added to gradle).
- I can generate the required Django REST endpoints in your backend and add them to the ZIP so the app works out-of-the-box.
- I can adapt auth header format (Token vs Bearer) to your backend if you tell me which one you use.

Enjoy — cuando quieras que integre los endpoints en tu backend, dímelo y los agrego también al ZIP.

