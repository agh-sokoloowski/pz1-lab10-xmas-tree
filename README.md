# Laboratorium 10: rysujemy choinkę

Użyjemy biblioteki graficznej Swing, a dokładniej klasy potomnej `JPanel`.

Umieść w klasie głównej poniższą funkcję main()

```java
public static void main(String[] args) {
// write your code here
    JFrame frame = new JFrame("Choinka");
    frame.setContentPane(new DrawPanel());
    frame.setSize(1000, 700);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setResizable(true);
    frame.setVisible(true);
}
```

## DrawPanel

`DrawPanel()` to klasa, której na razie brakuje. Utwórz ją w projekcie i dodaj funkcję `paint(Graphics g)` z przykładowym kodem wypisującym tekst.

```java
public class DrawPanel extends JPanel {
    public void paintComponent(Graphics g){
        g.setFont(new Font("Helvetica", Font.BOLD, 18));
        g.drawString("Hello World", 20, 20);
        System.out.println("painting");
    }
}
```

Zaobserwuj, kiedy na konsoli wypisywane jest `painting`.

`Graphics` to kontekst graficzny. Obiekt przechowuje informacje o bieżących atrybutach rysowania (font, kolor) oraz pozwala wywołać funkcje umożliwiające rysowanie wektorów oraz obrazów.

Wypróbuj kilka funkcji do rysowania. Na przykład

- Rysowanie linii
    ```java
    g.drawLine(10, 10, 100, 100);
    ```
- Rysowanie elips
    ```java
    g.setColor(Color.yellow);
    g.fillOval(100, 101, 30, 30);
    g.setColor(Color.black);
    g.drawOval(100, 101, 30, 30);
    ```
- Rysowanie wieloboków
    ```java
    int x[]={286,253,223,200,148,119,69,45,0};
    int y[]={0,101,89,108,79,95,66,80,56};
    g.fillPolygon(x,y,x.length);
    ```
- Rysowanie obrazków  
    Jeżeli chciałbyś wyświetlić obrazek, należy wcześniej go załadować, np. przechowywać jako atrybut klasy, a następnie wyświetlić w funkcji `paint()` (nie należy ładować go w funkcji `paint`)
    ```java
    Image img = Toolkit.getDefaultToolkit().getImage("bird1.jpg");
    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    ```

## Graphics2D

`Graphics2D` jest rozszerzoną wersją kontekstu graficznego pozwalającą na bardziej zaawansowane operacje. Aby uzyskać dostęp do obiektu `Graphics2D` wystarczy rzutowanie.

```java
public void paintComponent(Graphics g){
    Graphics2D g2d = (Graphics2D) g;
}
```

Ważną cechą `Graphics2D` jest możliwość transformacji (transformacji afinicznej) układu współrzędnych odpowiadających powinowactwom (~ klasy I LO), czyli:

- przesuniecia
- skalowanie
- rotacje

Postać macierzowa

- Wszystkie te operacje mogą być reprezentowane w postaci macierzy transformacji (3×3 dla 2D, 4×4 dla 3D).
- Zobacz przykłady na [https://en.wikipedia.org/wiki/Affine_transformation#Image_transformation](https://en.wikipedia.org/wiki/Affine_transformation#Image_transformation)
- Zastosowanie kilku operacji następujących po sobie można uzyskać mnożąc macierze, czyli np. funkcja Grapics2D.rotate() mnoży bieżącą macierz przez macierz rotacji.
- Macierz transformacji można zapisać w zmiennej lokalnej i załadować z powrotem do kontekstu graficznego.

Poniższy kod rysuje 12 linii obracając je o 30 stopni

```java
// zachowaj macierz przekształcenia
AffineTransform mat = g2d.getTransform();
// przesuń początek układu
g2d.translate(100,100);
// zastosuj skalowanie
g2d.scale(.2,.2);
// narysuj linie
for(int i=0;i<12;i++){
    g2d.drawLine(0,0,100,100);
    g2d.rotate(2*Math.PI/12);
}
//oddtwórz poprzednie ustawienia transformacji układu współrzędnych
g2d.setTransform(mat);
```

Analogicznie możesz obrócić tekst podczas rysowania...

```java
g2d.translate(200, 200);
// zastosuj skalowanie
g2d.scale(.2, .2);
g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
Font font = new Font("Serif", Font.PLAIN, 96);
g2d.setFont(font);
for (int i = 0; i < 12; i++) {
    g2d.drawString("Happy new year", 150, 0);
    g2d.rotate(2 * Math.PI / 12);
}
```

Możesz ustawić atrybuty linii...

```java
// zachowaj macierz przekształcenia
AffineTransform mat = g2d.getTransform();
// przesuń początek układu
g2d.translate(200, 200);
// zastosuj skalowanie
g2d.scale(.2, .2);
g2d.setStroke(new BasicStroke(50, CAP_ROUND, JOIN_MITER));
for(int i = 0; i < 12; i++) {
    //g2d.drawString("Happy new year", 150, 0);
    g2d.drawLine(0, 0, 100, 100);
    g2d.rotate(2 * Math.PI / 12);
}
// odtwórz poprzednie ustawienia transformacji układu współrzędnych
g2d.setTransform(mat);
```

Możesz też użyć gradientowego wypełnienia dla wieloboków

```java
Graphics2D g2d = (Graphics2D) g;
AffineTransform mat = g2d.getTransform();
GradientPaint grad = new GradientPaint(0, 0, new Color(0, 255, 0), 0, 100, new Color(0, 10, 0));
g2d.setPaint(grad);
g2d.translate(0, 50);
g2d.scale(0.7, 0.5);
int x[] = {286, 286, 223, 200, 148, 119, 69, 45, 0};
int y[] = {0, 131, 89, 108, 79, 95, 66, 80, 56};
g2d.fillPolygon(x, y, x.length);
g2d.translate(670, 0);
g2d.scale(-1, 1);
g2d.fillPolygon(x, y, x.length);
g2d.setTransform(mat);
```

### Tło
Możesz ustawić tło wołając metodę `setBackground()` klasy `JPanel`

Na przykład

```java
DrawPanel(){
    setBackground(new Color(0, 0, 50));
    // setOpaque(true);
}
```

Nie zapomnij na początku `paintComponent()` wywołać metodę `paintComponent()` nadklasy

## Rysowanie choinki

W zasadzie można narysować choinkę składając ją z powtarzalnych elementów: gałęzie, ozdoby, świeczki, itp. Może być to jedna długa funkcja, w której wołany jest np. kod do rysowania odpowiednio przesuniętych i przeskalowanych gałęzi, ozdób, lampek itp.

Zamiast tego, zdefiniujemy klasy elementów składowych

### Klasa bazowa (interfejs bazowy)
W zasadzie powinien nazywać sie `Shape`, ale taki już jest [https://docs.oracle.com/javase/8/docs/api/java/awt/Shape.html](https://docs.oracle.com/javase/8/docs/api/java/awt/Shape.html)

Nazwiemy go `XmasShape`

```java
public interface XmasShape {
    /**
     * Przesuwa poczatek układu w zadane miejsce, skaluje, jeśli trzeba obraca
     * @param g2d Graphics2D - kontekst graficzny 
     */
    void transform(Graphics2D g2d);
 
    /**
     * Zawiera kod, który rysuje elementy
     * @param g2d Graphics2D - kontekst graficzny
     */
    void render(Graphics2D g2d);
 
    /**
     * Standardowa implementacja metody
     * @param g2d
     */
    default void draw(Graphics2D g2d) {
        // Get the current transform
        AffineTransform saveAT = g2d.getTransform();
        // Perform transformation
        transform(g2d);
        // Render
        render(g2d);
        // Restore original transform
        g2d.setTransform(saveAT);
    }
}
```

### Napisz klasę Bubble

To ma być jedna z ozdób

```java
public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;
 
    @Override
    public void render(Graphics2D g2d) {
        // ustaw kolor wypełnienia
        g2d.fillOval(0, 0, 100, 100);
        // ustaw kolor obramowania
        g2d.drawOval(0, 0, 100, 100);
    }
 
    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}
```

Uwagi:

- obiekt zawsze jest rysowany tak samo (funkcja `render`)
- zmienia się położenie i skala obiektu; jest ona interpretowana w funkcji `transform`

### Refaktoryzacja DrawPanel

Dodaj atrybut

```java
List<XmasShape> shapes = new ArrayList<>();
```

Zmień funkcję `paintComponent()` na następującą:

```java
public void paintComponent(Graphics g){
    super.paintComponent(g);
    for (XmasShape s : shapes){
        s.draw((Graphics2D) g);
    }
}
```

### Dodaj obiekty

Dodaj kilka obiektów klasy `Bubble` i sprawdź, czy wyświetlane są zgodnie z oczekiwaniami. Napisz osobną funkcję do dodawania obiektów w `DrawPanel` i wywołaj ją w konstruktorze lub funkcji `main()`. Nie dodawaj obiektów w `paintComponent()`

### Kolejne klasy

- Dodaj klasę `Branch` - zielona gałąź drzewa
- Możesz utworzyć klasę `Tree` zawierającą listę `XmasShape` i złożyć gotowe drzewko z gałęzi
- Dodaj klasę `Light`
- Możesz dodać gwiazdki i inne ozdoby...

Za każdym razem:

- określ, jakie atrybuty są potrzebne, aby przeprowadzić transformację układu współrzędnych
- napisz funkcję `transform()`
- napisz kod rysujący element – `render()`