package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;

public class Main extends Application {
    public static int turn = 1;
    public static int[][] matrixA = new int[3][3];
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrixA[i][j]=0;
            }
        }//-1_X 1_O
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("крестики-нолики");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid.add(createButton(primaryStage), i, j);
            }
        }
        primaryStage.show();
    }

    private static Button createButton(Stage stage) {
        Button b = new Button(" ");
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int sum1=0, sum2=0;
                if (turn % 2 != 0) {
                    b.setText("X");
                } else {
                    b.setText("O");
                }

                int row = GridPane.getRowIndex(b);
                int col = GridPane.getColumnIndex(b);
                System.out.println("Turn = " + turn + " Row = " + row + " Col = " + col);
                int res=-1, sum3=0, sum4=0;
                if (turn%2==0)
                    res =1;
                matrixA[row][col]=res;
                for (int i = 0; i < 3; i++) {
                    sum1=0;
                    sum2=0;
                    for (int j = 0; j < 3; j++) {
                        sum1=sum1+matrixA[i][j];
                        sum2=sum2+matrixA[j][i];
                        if (i==j) {
                            sum3=sum3+matrixA[i][j];
                        }
                        if (i+j==2)
                        {
                            sum4=sum4+matrixA[i][j];
                        }
                    }
                    if (sum1 == 3 || sum2 ==3 || sum3 ==3|| sum4==3)
                    {
                        Text text = new Text("O выиграл");
                        Res(stage, text);
                        //System.exit(0);
                    }
                    else if (sum1 == -3 || sum2 ==-3 || sum3==-3||sum4==-3)
                    {
                        Text text = new Text("X выиграл");
                        Res(stage, text);
                        // System.exit(0);
                    }
                    sum1=0;
                    for (int k=0; k<3; k++)
                    {
                        for (int l=0; l<3; l++)
                        {
                            if (matrixA[l][k]!=0)
                                sum1=sum1+1;
                        }
                    }
                    if (sum1==9){
                        Text text = new Text("Ничья");
                        Res(stage, text);
                    }

                }
                b.setDisable(true);
                turn++;
            }
        });
        return b;
    }
    private static void Res(Stage stage, Text text)
    {
        text.setLayoutY(100);
        text.setLayoutX(115);
        Group group = new Group(text);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.setWidth(300);
        stage.setHeight(250);
        stage.show();
    }
}
