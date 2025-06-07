# MiniExcel

MiniExcel es una aplicación de escritorio desarrollada en Java Swing que simula una hoja de cálculo básica, permitiendo la edición de celdas, uso de fórmulas simples y la importación/exportación de archivos CSV.

---

## Estructura Principal

### **MainView**
- Ventana principal de la aplicación, extiende `JFrame`.
- Contiene la tabla de celdas, menú de archivo (abrir/guardar) y un campo para mostrar/editar fórmulas.

### **CeldaTableModel**
- Modelo de tabla personalizado que gestiona la lógica de las celdas, su edición y notificación de cambios.

### **Celda**
- Representa una celda individual.
- Encapsula la fórmula, valor y coordenadas usadas.

### **CeldaController**
- Controlador de lógica para cada celda.
- Maneja el parseo y validación de fórmulas, referencias a otras celdas y actualización de valores.

### **Coordenada**
- Clase de utilidad para manejar referencias de celdas (por ejemplo, `A1`, `B2`).

### **Renderers y Listeners**
- Personalizan la visualización de celdas y gestionan eventos de teclado y edición.

---

## Principales Funcionalidades

- **Edición de celdas**: Cada celda puede contener un valor o una fórmula (iniciada con `=`).
- **Fórmulas soportadas**:
    - Suma: `=SUM(A1;B2)`
    - Multiplicación: `=MULTIPLICATION(A1;B2)`
    - Referencias a otras celdas.
- **Importar/Exportar CSV**: Permite abrir y guardar hojas de cálculo en formato CSV, manejando correctamente valores y fórmulas.
- **Actualización automática**: Si una celda referenciada en una fórmula cambia, las celdas dependientes se actualizan automáticamente.
- **Interfaz gráfica**: Basada en Java Swing, con menú de archivo, campo de fórmulas y tabla editable.

---

## Comentarios y Documentación Interna Relevante

### **CeldaController**
- Maneja la lógica de validación y cálculo de fórmulas.
- Guarda las coordenadas de celdas usadas en la fórmula para actualizar dependencias.

#### Métodos principales:
- `validateAction(String formula)`: Valida y ejecuta la acción de la fórmula.
- `isFormula(String formula)`: Determina si un texto es una fórmula.
- `convertirReferenciaCelda(String referencia)`: Convierte referencias tipo `A1` a coordenadas.
- `getValor()`, `setValor(String valor)`, `getFormula()`, `setFormula(String formula)`: Accesores y mutadores.

---

### **Celda**
- Encapsula un `CeldaController`.
- Métodos para obtener y establecer fórmula, valor y coordenadas usadas.
- `updateByChangeValueUsed()`: Actualiza la celda si alguna de sus dependencias cambia.

---

### **CeldaTableModel**
- Extiende `AbstractTableModel`.
- Gestiona una matriz de celdas.

#### Métodos sobrescritos:
- `getValueAt`
- `setValueAt`
- `isCellEditable`
- `getColumnName`

- Notifica cambios a la vista y listeners.

---

### **MainView**
- Inicializa la interfaz y la tabla.
- Métodos para abrir y guardar archivos CSV.
- Actualiza el campo de fórmula al seleccionar una celda.
- Renderiza la primera columna en gris para simular encabezados de filas.
- Maneja eventos de menú y edición de celdas.

---

## Ejemplo de Uso de Fórmulas

- Para sumar valores de `A1` y `B2`:  
    ```plaintext
    =SUM(A1;B2)
    ```
- Para multiplicar valores de `A1` y `B2`:  
    ```plaintext
    =MULTIPLICATION(A1;B2)
    ```

Las fórmulas pueden referenciar otras celdas y se actualizan automáticamente.

---


## Autoría

Desarrollado por:*FREDERICK MONTIEL*

