/* ***************************************************************
* Autor............: Charlesson Mendes Pereira
* Matricula........: 201710786
* Inicio...........: 06/04/2022
* Ultima alteracao.: 10/04/2022
* Nome.............: Principal
* Funcao...........: Resolver a condicao de corrida do problema
*                    sem espera ocupada, utilizando semaforo
*************************************************************** */

//Importacao de classes
import java.io.File;
import java.util.concurrent.Semaphore;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
//Fim da importacao de classes


public class Principal extends Application { //Inicio da classe principal
  //Declaracao de atributos
  private Scene scene;
  private Group root;

  private File fileImgFundo;
  private File fileImgFerreiro1;
  private File fileImgFerreiro2;
  private File fileImgGuerreiro1;
  private File fileImgGuerreiro2;
  private File fileImgGuerreiro3;
  private File fileImgCorrentes;
  private File fileImgEspada1;
  private File fileImgEspada2;
  private File fileImgMarchado1;
  private File fileImgMarchado2;
  private File fileImgEscudo1;
  private File fileImgEscudo2;

  private ImageView imgFundo;
  private ImageView imgFerreiro1;
  private ImageView imgFerreiro2;
  private ImageView imgGuerreiro1;
  private ImageView imgGuerreiro2;
  private ImageView imgGuerreiro3;
  private ImageView imgCorrentes;
  private ImageView imgEspada1;
  private ImageView imgEspada2;
  private ImageView imgMarchado1;
  private ImageView imgMarchado2;
  private ImageView imgEscudo1;
  private ImageView imgEscudo2;

  private Produtor ferreiro;
  private Consumidor guerreiro1;
  private Consumidor guerreiro2;
  private Consumidor guerreiro3;

  private Rectangle crtlFerreiro;
  private Rectangle crtlGuerreiro1;
  private Rectangle crtlGuerreiro2;
  private Rectangle crtlGuerreiro3;


  private Slider sldFerreiro;
  private Slider sldGuerreiro1;
  private Slider sldGuerreiro2;
  private Slider sldGuerreiro3;

  private Text txtSldFerreiro;
  private Text txtSldGuerreiro1;
  private Text txtSldGuerreiro2;
  private Text txtSldGuerreiro3;

  private Semaphore mutex;
  private Semaphore empty;
  private Semaphore full;

  private Buffer buffer;

  private Button btn;
  //Fim da declaracao de atributos

  /* ***************************************************************
  * Metodo: start
  * Funcao: Ponto de partida de aplicacoes javafx
  * Parametros: stage da classe Stage
  * Retorno: Sem retorno
  *************************************************************** */
  @Override
  public void start(Stage stage) throws Exception { //Inicio do metodo start
    inicializar(); //Chamando o metodo inicializar
    stage.setTitle("Produtor-consumidor"); //Setando o titulo da janela
    stage.setResizable(false); //Setando a propriedade redimensionavel
    stage.setScene(scene); //Setando a scene do stage
    stage.show(); //Mostrando a janela
  } //Fim do metodo start

  /* ***************************************************************
  * Metodo: inicializar
  * Funcao: Inicializar os atributos
  * Parametros: Sem parametros
  * Retorno: Sem retorno
  *************************************************************** */
  private void inicializar() { //Inicio do metodo inicializar
    root = new Group();

    this.mutex = new Semaphore(1);
    this.empty = new Semaphore(6);
    this.full = new Semaphore(0);

    fileImgFundo = new File("/imagens/fundo.jpg"); //Instanciando atributo fileImgFundo

    imgFundo = new ImageView(fileImgFundo.getPath()); //Instanciando atributo imgFundo
    imgFundo.setPreserveRatio(true); //Habilitando propriedade de proporcao do atributo imgFundo
    imgFundo.setSmooth(true); //Habilitando a propriedade smooth
    imgFundo.setFitWidth(930); //Setando a largura do atributo imgFundo
    imgFundo.setX(35); //Setando a coordenada x do atributo imgFundo   

    fileImgFerreiro1 = new File("/imagens/ferreiro1.png");
    imgFerreiro1 = new ImageView(fileImgFerreiro1.getPath()); //Instanciando imgFerreiro
    imgFerreiro1.setY(330); //Setando a coordenada y do atributo imgFerreiro1
    crtlFerreiro = new Rectangle(50, 40, 250, 100);
    crtlFerreiro.setFill(Color.WHITE);
    crtlFerreiro.setStroke(Color.BLACK);
    crtlFerreiro.setStrokeWidth(3);
    crtlFerreiro.setArcHeight(20);
    crtlFerreiro.setArcWidth(20);
    crtlFerreiro.setOpacity(0.6);

    fileImgFerreiro2 = new File("/imagens/ferreiro2.png"); //Instanciando atributo fileImgFerreiro2
    imgFerreiro2 = new ImageView(fileImgFerreiro2.getPath()); //Instanciando atributo imgFerreiro2
    imgFerreiro2.setY(255); //Setando a coordenada y do atributo imgFerreiro2
    imgFerreiro2.setVisible(false); //Desabilitando a visibilidade do atributo imgFerreiro2

    fileImgGuerreiro1 = new File("/imagens/guerreiro1.png"); //Instanciando atributo fileImgGuerreiro1
    imgGuerreiro1 = new ImageView(fileImgGuerreiro1.getPath()); //Instanciando atributo imgGuerreiro1
    imgGuerreiro1.setX(1000); //Setando a coordenada x do atributo imgGuerreiro1
    imgGuerreiro1.setY(450); //Setando a coordenada y do atributo imgGuerreiro1
    crtlGuerreiro1 = new Rectangle(940, 40, 250, 100);
    crtlGuerreiro1.setFill(Color.WHITE);
    crtlGuerreiro1.setStroke(Color.GRAY);
    crtlGuerreiro1.setStrokeWidth(3);
    crtlGuerreiro1.setArcHeight(20);
    crtlGuerreiro1.setArcWidth(20);
    crtlGuerreiro1.setOpacity(0.6);
    
    fileImgGuerreiro2 = new File("/imagens/guerreiro2.png"); //Instanciando atributo fileImgGuerreiro2
    imgGuerreiro2 = new ImageView(fileImgGuerreiro2.getPath()); //Instanciando o atributo imgGuerreiro2
    imgGuerreiro2.setX(1000); //Setando a coordenada x do atributo imgGuerreiro2
    imgGuerreiro2.setY(450); //Setando a coordenada y do atributo imgGuerreiro2
    crtlGuerreiro2 = new Rectangle(940, 160, 250, 100);
    crtlGuerreiro2.setFill(Color.WHITE);
    crtlGuerreiro2.setStroke(Color.GRAY);
    crtlGuerreiro2.setStrokeWidth(3);
    crtlGuerreiro2.setArcHeight(20);
    crtlGuerreiro2.setArcWidth(20);
    crtlGuerreiro2.setOpacity(0.6);


    fileImgGuerreiro3 = new File("/imagens/guerreiro3.png"); //Instanciando atributo fileImgGuerreiro2
    imgGuerreiro3 = new ImageView(fileImgGuerreiro3.getPath()); //Instanciando atributo imgGuerreiro3
    imgGuerreiro3.setX(1000); //Setando a coordenada x do atributo imgGuerreiro3
    imgGuerreiro3.setY(450); //Setando a coordenada y do atributo imgGuerreiro3
    crtlGuerreiro3 = new Rectangle(940, 280, 250, 100);
    crtlGuerreiro3.setFill(Color.WHITE);
    crtlGuerreiro3.setStroke(Color.GRAY);
    crtlGuerreiro3.setStrokeWidth(3);
    crtlGuerreiro3.setArcHeight(20);
    crtlGuerreiro3.setArcWidth(20);
    crtlGuerreiro3.setOpacity(0.6);

    fileImgCorrentes = new File("/imagens/correntes.png"); //Instancianto atributo fileImgCorrentes
    imgCorrentes = new ImageView(fileImgCorrentes.getPath()); //Instanciando atributo imgCorrentes
    imgCorrentes.setX(330); //Setando a coordenada x do atributo imgCorrentes
    imgCorrentes.setY(100);

    fileImgEspada1 = new File("/imagens/espada1.png"); //Instanciando atributo fileImgEspada1
    imgEspada1 = new ImageView(fileImgEspada1.getPath()); //Instanciando atributo imgEspada1
    imgEspada1.setVisible(false);
    imgEspada1.setX(360); //Setando a coordenada x do atributo imgEspada1
    imgEspada1.setY(130); //Setando a coordenada y do atributo imgEspada1

    fileImgEspada2 = new File("/imagens/espada2.png"); //Instanciando atributo fileImgEspada2
    imgEspada2 = new ImageView(fileImgEspada2.getPath()); //Instanciando atributo imgEspada2
    imgEspada2.setVisible(false);
    imgEspada2.setX(360); //Setando coordenada x do atributo imgEspada2
    imgEspada2.setY(400); //Setando coordenada y do atributo imgEspada2

    fileImgMarchado1 = new File("/imagens/marchado1.png"); //Instanciando atributo fileImgMarchado1
    imgMarchado1 = new ImageView(fileImgMarchado1.getPath()); //Instanciando atributo imgMarchado1
    imgMarchado1.setVisible(false);
    imgMarchado1.setX(590); //Setando coordenada x do atributo imgMarchado1
    imgMarchado1.setY(130); //Setando coordenada y do atributo imgMarchado1

    fileImgMarchado2 = new File("/imagens/marchado2.png"); //Instanciando atributo fileImgMarchado2
    imgMarchado2 = new ImageView(fileImgMarchado2.getPath()); //Instanciando atributo imgMarchado2
    imgMarchado2.setVisible(false);
    imgMarchado2.setX(570); //Setando coordenada x do atributo imgMarchado2
    imgMarchado2.setY(400); //Setando coordenada y do atributo imgMarchado2

    fileImgEscudo1 = new File("/imagens/escudo1.png"); //Instanciando atributo fileImgEscudo1
    imgEscudo1 = new ImageView(fileImgEscudo1.getPath()); //Instanciando atributo imgEscuto1
    imgEscudo1.setVisible(false);
    imgEscudo1.setX(800); //Setando a coordenada x do atributo imgEscudo1
    imgEscudo1.setY(130); //Setando a coordenady x do atributo imgEscudo1

    fileImgEscudo2 = new File("/imagens/escudo2.png"); //Instanciando atributo fileImgEscudo2
    imgEscudo2 = new ImageView(fileImgEscudo2.getPath()); //Instanciando atributo imgEscuto2
    imgEscudo2.setVisible(false);
    imgEscudo2.setX(800); //Setando a coordenada x do atributo imgEscudo2
    imgEscudo2.setY(400); //Setando a coordenady x do atributo imgEscudo2

    buffer = new Buffer();

    ferreiro = new Produtor(root, buffer, imgFerreiro1, imgFerreiro2, fileImgEspada1, fileImgEspada2, fileImgMarchado1,
    fileImgMarchado2, fileImgEscudo1, fileImgEscudo2, mutex, empty, full);
    
    txtSldFerreiro = new Text(String.format("%02ds", 1));
    txtSldFerreiro.setFont(Font.font("Arial", 30));
    txtSldFerreiro.setX(250);
    txtSldFerreiro.setY(100);
    sldFerreiro = new Slider(1, 10, 1);
    sldFerreiro.setBlockIncrement(1);
    sldFerreiro.setShowTickMarks(true);
    sldFerreiro.setShowTickLabels(true);
    sldFerreiro.setPrefWidth(180);
    sldFerreiro.setMajorTickUnit(1);
    sldFerreiro.setMinorTickCount(4);
    sldFerreiro.setLayoutX(60);
    sldFerreiro.setLayoutY(80);
    sldFerreiro.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
        // TODO Auto-generated method stub
        ferreiro.setTempoProducao((int) sldFerreiro.getValue());
        txtSldFerreiro.setText(String.format("%02ds", (int) sldFerreiro.getValue()));
      }      
    });

    guerreiro1 = new Consumidor(buffer, imgGuerreiro1, mutex, empty, full);

    txtSldGuerreiro1 = new Text(String.format("%02ds", 1));
    txtSldGuerreiro1.setFont(Font.font("Arial", 30));
    txtSldGuerreiro1.setX(1140);
    txtSldGuerreiro1.setY(100);
    sldGuerreiro1 = new Slider(1, 10, 1);
    sldGuerreiro1.setBlockIncrement(1);
    sldGuerreiro1.setShowTickMarks(true);
    sldGuerreiro1.setShowTickLabels(true);
    sldGuerreiro1.setPrefWidth(180);
    sldGuerreiro1.setMajorTickUnit(1);
    sldGuerreiro1.setMinorTickCount(4);
    sldGuerreiro1.setLayoutX(950);
    sldGuerreiro1.setLayoutY(80);
    sldGuerreiro1.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
        // TODO Auto-generated method stub
        guerreiro1.setTempoConsumo((int) sldGuerreiro1.getValue());
        txtSldGuerreiro1.setText(String.format("%02ds", (int) sldGuerreiro1.getValue()));
      }      
    });

    guerreiro2 = new Consumidor(buffer, imgGuerreiro2, mutex, empty, full);

    txtSldGuerreiro2 = new Text(String.format("%02ds", 1));
    txtSldGuerreiro2.setFont(Font.font("Arial", 30));
    txtSldGuerreiro2.setX(1140);
    txtSldGuerreiro2.setY(220);
    sldGuerreiro2 = new Slider(1, 10, 1);
    sldGuerreiro2.setBlockIncrement(1);
    sldGuerreiro2.setShowTickMarks(true);
    sldGuerreiro2.setShowTickLabels(true);
    sldGuerreiro2.setPrefWidth(180);
    sldGuerreiro2.setMajorTickUnit(1);
    sldGuerreiro2.setMinorTickCount(4);
    sldGuerreiro2.setLayoutX(950);
    sldGuerreiro2.setLayoutY(200);
    sldGuerreiro2.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
        // TODO Auto-generated method stub
        guerreiro2.setTempoConsumo((int) sldGuerreiro2.getValue());
        txtSldGuerreiro2.setText(String.format("%02ds", (int) sldGuerreiro2.getValue()));
      }      
    });

    guerreiro3 = new Consumidor(buffer, imgEscudo2, mutex, empty, full);

    txtSldGuerreiro3 = new Text(String.format("%02ds", 1));
    txtSldGuerreiro3.setFont(Font.font("Arial", 30));
    txtSldGuerreiro3.setX(1140);
    txtSldGuerreiro3.setY(340);
    sldGuerreiro3 = new Slider(1, 10, 1);
    sldGuerreiro3.setBlockIncrement(1);
    sldGuerreiro3.setShowTickMarks(true);
    sldGuerreiro3.setShowTickLabels(true);
    sldGuerreiro3.setPrefWidth(180);
    sldGuerreiro3.setMajorTickUnit(1);
    sldGuerreiro3.setMinorTickCount(4);
    sldGuerreiro3.setLayoutX(950);
    sldGuerreiro3.setLayoutY(320);
    sldGuerreiro3.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
        // TODO Auto-generated method stub
        guerreiro3.setTempoConsumo((int) sldGuerreiro3.getValue());
        txtSldGuerreiro3.setText(String.format("%02ds", (int) sldGuerreiro3.getValue()));
      }      
    });

    btn = new Button("Apertar");
    btn.setLayoutX(300);
    btn.setLayoutY(100);
    btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          ferreiro.start();
          guerreiro1.start();
          //guerreiro2.start();
          //guerreiro3.start();
        }
    });

    root.getChildren().addAll(imgFundo, imgFerreiro1,imgCorrentes, imgFerreiro2, btn, imgGuerreiro1, imgGuerreiro2,
    imgGuerreiro3, crtlFerreiro, crtlGuerreiro1, crtlGuerreiro2, crtlGuerreiro3, txtSldFerreiro, sldFerreiro,
    txtSldGuerreiro1, sldGuerreiro1, txtSldGuerreiro2, sldGuerreiro2, txtSldGuerreiro3, sldGuerreiro3); //Instanciando atributo root
    scene = new Scene(root, 1200, 690, Color.BLACK); //Instanciando atributo scene
  } //Fim do metodo inicializar

  /* ***************************************************************
  * Metodo: main
  * Funcao: Ponto de partida de programas java
  * Parametros: Vetor de Strings
  * Retorno: Sem retorno
  *************************************************************** */
  public static void main(String[] args) { //Inicio do metodo main
    launch(args); //Chamando metodo launch
  } //Fim do metodo main
} //Fim da classe principal