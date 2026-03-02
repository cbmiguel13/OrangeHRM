# OrangeHRM - Proyecto de Automatizacion

Proyecto de automatizacion de pruebas para la aplicacion OrangeHRM, con pruebas de UI, API y Performance.

## Carpeta publica del Drive
`https://drive.google.com/drive/folders/1WXfraQm5csIi7oeBdaTKpEU_u23x8PP6?usp=drive_link`

---

## Configuración pruebas UI

Se creo el archivo `src/test/resources/config.properties` donde se consume la informacion para la ejecucion de la prueba:

    # URL
    url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

    # Login
    username=Admin
    password=admin123

    # Informacion Empleado
    firstName=Nicolas
    middleName=Andres
    lastName=Copernico
    imagePath=src\\test\\resources\\copernico.jpg

    # BrowserStack
    useBrowserStack=true
    bs.username=luiscastaeda_38TFxY
    bs.accesskey=wyw7pyopyvEwAuCT7juT


---

## Ejecución de pruebas

### Pruebas UI — BrowserStack
En `src/test/resources/config.properties` cambiar la propiedad de `useBrowserStack` a `true` y luego ejecutar normalmente

### Pruebas API — Postman
1. Descargar el archivo `OrangeHRM API Tests.postman_collection.json` que esta ubicado en el Drive (`Orange HRM/Codigo/Api - Postman/OrangeHRM.postman_collection.json`)
2. Importa el archivo `OrangeHRM API Tests.postman_collection.json` en Postman
3. Ejecutar

### Pruebas Performance — JMeter
1. Descargar el archivo `OrangeHRM.jmx` que esta ubicado en el Drive (`Orange HRM/Codigo/JMeter/OrangeHRM.jmx`)
1. Abre el archivo `OrangeHRM.jmx` en JMeter
2. Ejecutar

---

## Flujo de pruebas UI automatizadas

```
1. Realizar login
2. Registrar empleado en módulo PIM con la informacion basica (Nombres, apellidos y foto de perfil)
3. Búsqueda del empleado en módulo Directory y confirmar informacion ingresada
```

---

## Reportes
### UI (ExtentReports)
Ubicado en el Drive (`Orange HRM/Reporte/Automatizacion/TestReport.html`)
### API (Postman)
Ubicado en el Drive (`Orange HRM/Reporte/Api - Postman/OrangeHRM.postman_test_run.json`)
### Performance (JMeter)
Ubicado en el Drive (`Orange HRM/Reporte/JMeter/ReporteJMETER.xlsx`)

---

## CI/CD

El pipeline se ejecuta automáticamente al hacer push a la rama `main`. Los reportes quedan disponibles en **GitHub Actions → Artifacts**.

---

## Autor
**cbmiguel13** — Prueba técnica de automatización
