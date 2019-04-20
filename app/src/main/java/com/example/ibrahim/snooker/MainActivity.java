package com.example.ibrahim.snooker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView playerOneName,
            playerTwoName,

            playerOneScore,
            playerTwoScore,

            pockedRedBallsOne,
            pockedYellowBallsOne,
            pockedGreenBallsOne,
            pockedBrownBallsOne,
            pockedBlueBallsOne,
            pockedPinkBallsOne,
            pockedBlackBallsOne,

            pockedRedBallsTwo,
            pockedYellowBallsTwo,
            pockedGreenBallsTwo,
            pockedBrownBallsTwo,
            pockedBlueBallsTwo,
            pockedPinkBallsTwo,
            pockedBlackBallsTwo,

            playerOneFouls,
            playerTwoFouls,

            redBallsOnTable,
            yellowBallsOnTable,
            greenBallsOnTable,
            brownBallsOnTable,
            blueBallsOnTable,
            pinkBallsOnTable,
            blackBallsOnTable;

    private Button wrongShot, restartGame;

    /*
     * To know which ball that players currently playing with
     */
    private boolean redBallsAllowed = true;
    private boolean otherBallsAllowed = false;
    private boolean playerOneTurn = true;
    private boolean playerTwoTurn = false;

    /*
     * When the red balls are not on the table players must pot balls in sequence
     */
    private int potBallsInOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneName = findViewById(R.id.team_a);
        playerTwoName = findViewById(R.id.team_b);

        playerOneScore = findViewById(R.id.team_a_score);
        playerTwoScore = findViewById(R.id.team_b_score);

        pockedRedBallsOne = findViewById(R.id.red_ball_team_a_txt);
        pockedYellowBallsOne = findViewById(R.id.yellow_ball_team_a_txt);
        pockedGreenBallsOne = findViewById(R.id.green_ball_team_a_txt);
        pockedBrownBallsOne = findViewById(R.id.brown_ball_team_a_txt);
        pockedBlueBallsOne = findViewById(R.id.blue_ball_team_a_txt);
        pockedPinkBallsOne = findViewById(R.id.pink_ball_team_a_txt);
        pockedBlackBallsOne = findViewById(R.id.black_ball_team_a_txt);

        pockedRedBallsTwo = findViewById(R.id.red_ball_team_b_txt);
        pockedYellowBallsTwo = findViewById(R.id.yellow_ball_team_b_txt);
        pockedGreenBallsTwo = findViewById(R.id.green_ball_team_b_txt);
        pockedBrownBallsTwo = findViewById(R.id.brown_ball_team_b_txt);
        pockedBlueBallsTwo = findViewById(R.id.blue_ball_team_b_txt);
        pockedPinkBallsTwo = findViewById(R.id.pink_ball_team_b_txt);
        pockedBlackBallsTwo = findViewById(R.id.black_ball_team_b_txt);

        playerOneFouls = findViewById(R.id.team_a_fouls);
        playerTwoFouls = findViewById(R.id.team_b_fouls);

        redBallsOnTable = findViewById(R.id.red_balls_remain);
        yellowBallsOnTable = findViewById(R.id.yellow_ball_remain);
        greenBallsOnTable = findViewById(R.id.green_ball_remain);
        brownBallsOnTable = findViewById(R.id.brown_ball_remain);
        blueBallsOnTable = findViewById(R.id.blue_ball_remain);
        pinkBallsOnTable = findViewById(R.id.pink_ball_remain);
        blackBallsOnTable = findViewById(R.id.black_ball_remain);

        wrongShot = findViewById(R.id.foul);
        restartGame = findViewById(R.id.reset);

        redBallsOnTable.setOnClickListener(playersTurn);
        yellowBallsOnTable.setOnClickListener(playersTurn);
        greenBallsOnTable.setOnClickListener(playersTurn);
        brownBallsOnTable.setOnClickListener(playersTurn);
        blueBallsOnTable.setOnClickListener(playersTurn);
        pinkBallsOnTable.setOnClickListener(playersTurn);
        blackBallsOnTable.setOnClickListener(playersTurn);

        restartGame.setOnClickListener(restart);
        wrongShot.setOnClickListener(missedShot);
    }
    View.OnClickListener missedShot = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(playerOneTurn) {
                endOfPlayerOneTurn();
                playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText()))+4));
            }
            else /*(playerTwoTurn)*/ {
                endOfPlayerTwoTurn();
                playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText()))+4));
            }
        }
    };
    View.OnClickListener restart = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            redBallsOnTable.setText(String.valueOf(15));
            yellowBallsOnTable.setText(String.valueOf(1));
            greenBallsOnTable.setText(String.valueOf(1));
            brownBallsOnTable.setText(String.valueOf(1));
            blueBallsOnTable.setText(String.valueOf(1));
            pinkBallsOnTable.setText(String.valueOf(1));
            blackBallsOnTable.setText(String.valueOf(1));

            pockedRedBallsOne.setText(String.valueOf(0));
            pockedYellowBallsOne.setText(String.valueOf(0));
            pockedGreenBallsOne.setText(String.valueOf(0));
            pockedBrownBallsOne.setText(String.valueOf(0));
            pockedBlueBallsOne.setText(String.valueOf(0));
            pockedPinkBallsOne.setText(String.valueOf(0));
            pockedBlackBallsOne.setText(String.valueOf(0));

            pockedRedBallsTwo.setText(String.valueOf(0));
            pockedYellowBallsTwo.setText(String.valueOf(0));
            pockedGreenBallsTwo.setText(String.valueOf(0));
            pockedBrownBallsTwo.setText(String.valueOf(0));
            pockedBlueBallsTwo.setText(String.valueOf(0));
            pockedPinkBallsTwo.setText(String.valueOf(0));
            pockedBlackBallsTwo.setText(String.valueOf(0));

            redBallsOnTable.setClickable(true);
            yellowBallsOnTable.setClickable(true);
            greenBallsOnTable.setClickable(true);
            brownBallsOnTable.setClickable(true);
            blueBallsOnTable.setClickable(true);
            pinkBallsOnTable.setClickable(true);
            blackBallsOnTable.setClickable(true);

            playerOneName.setTextColor(Color.RED);
            playerTwoName.setTextColor(Color.WHITE);

            playerOneScore.setText(String.valueOf(0));
            playerTwoScore.setText(String.valueOf(0));

            playerOneFouls.setText(String.valueOf(0));
            playerTwoFouls.setText(String.valueOf(0));

            potBallsInOrder = 1;

            redBallsAllowed = true;
            otherBallsAllowed = false;
            playerOneTurn = true;
            playerTwoTurn = false;

            wrongShot.setEnabled(true);

        }
    };
    View.OnClickListener playersTurn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            /*
             * If red balls were not on the table then do follows
             */
            if (Integer.parseInt(String.valueOf(redBallsOnTable.getText())) == 0) {
                if(playerOneTurn) {

                    /*
                     * pot balls in order
                     * 1- yellow
                     * 2- green
                     * 3- brown
                     * 4- blue
                     * 5- pink
                     * 6- black
                     */
                    if(potBallsInOrder == 1) {
                        otherBallsTurnPlayerOne(view);
                        redBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == yellowBallsOnTable.getId() && potBallsInOrder == 2) {
                        yellowBallsOnTable.setText(String.valueOf(0));
                        playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 2));
                        yellowBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == greenBallsOnTable.getId() && potBallsInOrder == 3) {
                        greenBallsOnTable.setText(String.valueOf(0));
                        playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 3));
                        greenBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == brownBallsOnTable.getId() && potBallsInOrder == 4) {
                        brownBallsOnTable.setText(String.valueOf(0));
                        playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 4));
                        brownBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == blueBallsOnTable.getId() && potBallsInOrder == 5) {
                        blueBallsOnTable.setText(String.valueOf(0));
                        playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 5));
                        blueBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == pinkBallsOnTable.getId() && potBallsInOrder == 6) {
                        pinkBallsOnTable.setText(String.valueOf(0));
                        playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 6));
                        pinkBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == blackBallsOnTable.getId() && potBallsInOrder == 7) {
                        blackBallsOnTable.setText(String.valueOf(0));
                        playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 7));
                        blackBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                        wrongShot.setEnabled(false);
                        winner(view);
                    }

                    /*
                     * Wrong pocked ball
                     */
                    else {
                        otherBallsFoulPlayerOne(view);
                    }
                }
                else /*(playerTwoTurn)*/ {
                    if(potBallsInOrder == 1) {
                        otherBallsTurnPlayerTwo(view);
                        redBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == yellowBallsOnTable.getId() && potBallsInOrder == 2) {
                        yellowBallsOnTable.setText(String.valueOf(0));
                        playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 2));
                        yellowBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == greenBallsOnTable.getId() && potBallsInOrder == 3) {
                        greenBallsOnTable.setText(String.valueOf(0));
                        playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 3));
                        greenBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == brownBallsOnTable.getId() && potBallsInOrder == 4) {
                        brownBallsOnTable.setText(String.valueOf(0));
                        playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 4));
                        brownBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == blueBallsOnTable.getId() && potBallsInOrder == 5) {
                        blueBallsOnTable.setText(String.valueOf(0));
                        playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 5));
                        blueBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == pinkBallsOnTable.getId() && potBallsInOrder == 6) {
                        pinkBallsOnTable.setText(String.valueOf(0));
                        playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 6));
                        pinkBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                    }
                    else if(view.getId() == blackBallsOnTable.getId() && potBallsInOrder == 7) {
                        blackBallsOnTable.setText(String.valueOf(0));
                        playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 7));
                        blackBallsOnTable.setClickable(false);
                        potBallsInOrder++;
                        wrongShot.setEnabled(false);
                        winner(view);
                    }

                    /*
                     * Wrong pocked ball
                     */
                    else {
                        otherBallsFoulPlayerTwo(view);
                    }
                }
            }

            /*
             * if it was player one turn and red balls are allowed to shot then do the follows
             */
            else if(redBallsAllowed && playerOneTurn && view.getId() == redBallsOnTable.getId()) {

                /*
                 *  First, getText from pockedRedBalls it returns array of characters
                 *  Second, we have to convert this array of character to String
                 *  Third, convert String to integer
                 *  Fourth, add current integer by one
                 *  Fifth, again convert it to String
                 *  Sixth, setText of the current String
                 */
                pockedRedBallsOne.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedRedBallsOne.getText()))+1));
                decreaseNumberOfRedBalls();
                playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText()))+1));

                otherBallsTurn();
            }

            /*
             * if it was player two turn and red balls are allowed to shot then do the follows
             */
            else if(redBallsAllowed && playerTwoTurn && view.getId() == redBallsOnTable.getId()) {

                /*
                 *  First, getText from pockedRedBalls it returns array of characters
                 *  Second, we have to convert this array of character to String
                 *  Third, convert String to integer
                 *  Fourth, add current integer by one
                 *  Fifth, again convert it to String
                 *  Sixth, setText of the current String
                 */
                pockedRedBallsTwo.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedRedBallsTwo.getText()))+1));
                decreaseNumberOfRedBalls();
                playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText()))+1));

                otherBallsTurn();
            }

            /*
             * If player one hit red ball twice will get the foul and give the shot to player two
             */
            else if(view.getId() == redBallsOnTable.getId() && playerOneTurn) {
                playerOneFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneFouls.getText()))+1));
                playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText()))+4));
                decreaseNumberOfRedBalls();

                endOfPlayerOneTurn();
            }

            /*
             * If player two hit red ball twice will get the foul and give the shot to player one
             */
            else if(view.getId() == redBallsOnTable.getId() && playerTwoTurn) {
                playerTwoFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoFouls.getText()))+1));
                playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText()))+4));
                decreaseNumberOfRedBalls();

                endOfPlayerTwoTurn();
            }

            /*
             * If player one pocked a red ball and the other balls instead of red pocked, here will count as follows
             * Yellow = 2
             * Green = 3
             * Brown = 4
             * Blue = 5
             * Pink = 6
             * Black = 7
             */
            else if(otherBallsAllowed && playerOneTurn) {
                otherBallsTurnPlayerOne(view);
            }

            else if(otherBallsAllowed && playerTwoTurn) {
                otherBallsTurnPlayerTwo(view);
            }

            /*
             * If player one hit another balls in red ball turn will get foul
             */
            else if(playerOneTurn){
                otherBallsFoulPlayerOne(view);
            }

            /*
             * If player one hit another balls in red ball turn will get foul
             */
            else if(playerTwoTurn){
                otherBallsFoulPlayerTwo(view);
            }

        }
    };

    public void endOfPlayerOneTurn() {

        /*
         * End of player one turn
         */
        playerOneTurn = false;

        /*
         * Player two turn
         */
        playerTwoTurn = true;

        /*
         * Allow red balls
         */
        redBallsAllowed = true;

        /*
         * other balls are not allowable in red balls turn
         */
        otherBallsAllowed = false;

        /*
         * Change player name color
         */
        playerOneName.setTextColor(Color.WHITE);
        playerTwoName.setTextColor(Color.RED);
    }
    public void endOfPlayerTwoTurn() {
        /*
         * End of player two turn
         */
        playerTwoTurn = false;

        /*
         * Player one turn
         */
        playerOneTurn = true;

        /*
         * Allow red balls
         */
        redBallsAllowed = true;

        /*
         * other balls are not allowable in red balls turn
         */
        otherBallsAllowed = false;

        /*
         * Change player name color
         */
        playerOneName.setTextColor(Color.RED);
        playerTwoName.setTextColor(Color.WHITE);
    }
    public void decreaseNumberOfRedBalls() {
        redBallsOnTable.setText(String.valueOf(Integer.parseInt(String.valueOf(redBallsOnTable.getText()))-1));
    }
    public void redBallsTurn() {
        /*
         * Other balls are not allowed when they pocked
         */
        otherBallsAllowed = false;

        /*
         * Again red balls turn comes
         */
        redBallsAllowed = true;
    }

    // It just change turn from red balls to other balls
    public void otherBallsTurn() {
        /*
         * Red balls are not allowed now
         */
        redBallsAllowed = false;
        /*
         * Freely you can pock a yellow, green, brown, blue, pink, or black balls except red balls
         */
        otherBallsAllowed = true;
    }
    public void otherBallsFoulPlayerOne(View view) {
        /*
         * If player one hit yellow or green or blue all balls counts 4
         */
        if(view.getId() == yellowBallsOnTable.getId() || view.getId() == greenBallsOnTable.getId() || view.getId() == brownBallsOnTable.getId()) {
            playerOneFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneFouls.getText()))+1));
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText()))+4));
        }
        else if(view.getId() == blueBallsOnTable.getId()) {
            playerOneFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneFouls.getText()))+1));
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText()))+5));
        }
        else if(view.getId() == pinkBallsOnTable.getId()) {
            playerOneFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneFouls.getText()))+1));
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText()))+6));
        }
        else if(view.getId() == blackBallsOnTable.getId()) {
            playerOneFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneFouls.getText()))+1));
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText()))+7));
        }

        endOfPlayerOneTurn();
    }
    public void otherBallsFoulPlayerTwo(View view) {
        /*
         * If player two hit yellow or green or blue all balls counts 4
         */
        if(view.getId() == yellowBallsOnTable.getId() || view.getId() == greenBallsOnTable.getId() || view.getId() == brownBallsOnTable.getId()) {
            playerTwoFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoFouls.getText()))+1));
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText()))+4));
        }
        else if(view.getId() == blueBallsOnTable.getId()) {
            playerTwoFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoFouls.getText()))+1));
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText()))+5));
        }
        else if(view.getId() == pinkBallsOnTable.getId()) {
            playerTwoFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoFouls.getText()))+1));
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText()))+6));
        }
        else if(view.getId() == blackBallsOnTable.getId()) {
            playerTwoFouls.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoFouls.getText()))+1));
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText()))+7));
        }

        endOfPlayerTwoTurn();
    }
    // This function count the score of other balls
    public void otherBallsTurnPlayerOne(View view) {
        if (view.getId() == yellowBallsOnTable.getId()) {
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 2));
            pockedYellowBallsOne.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedYellowBallsOne.getText()))+1));
        }
        else if (view.getId() == greenBallsOnTable.getId()) {
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 3));
            pockedGreenBallsOne.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedGreenBallsOne.getText()))+1));
        }
        else if (view.getId() == brownBallsOnTable.getId()) {
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 4));
            pockedBrownBallsOne.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedBrownBallsOne.getText()))+1));
        }
        else if (view.getId() == blueBallsOnTable.getId()) {
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 5));
            pockedBlueBallsOne.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedBlueBallsOne.getText()))+1));
        }
        else if (view.getId() == pinkBallsOnTable.getId()) {
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 6));
            pockedPinkBallsOne.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedPinkBallsOne.getText()))+1));
        }
        else if (view.getId() == blackBallsOnTable.getId()) {
            playerOneScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerOneScore.getText())) + 7));
            pockedBlackBallsOne.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedBlackBallsOne.getText()))+1));
        }

        redBallsTurn();
    }
    public void otherBallsTurnPlayerTwo(View view) {
        if (view.getId() == yellowBallsOnTable.getId()) {
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 2));
            pockedYellowBallsTwo.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedYellowBallsTwo.getText()))+1));
        }
        else if (view.getId() == greenBallsOnTable.getId()) {
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 3));
            pockedGreenBallsTwo.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedGreenBallsTwo.getText()))+1));
        }
        else if (view.getId() == brownBallsOnTable.getId()) {
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 4));
            pockedBrownBallsTwo.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedBrownBallsTwo.getText()))+1));
        }
        else if (view.getId() == blueBallsOnTable.getId()) {
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 5));
            pockedBlueBallsTwo.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedBlueBallsTwo.getText()))+1));
        }
        else if (view.getId() == pinkBallsOnTable.getId()) {
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 6));
            pockedPinkBallsTwo.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedPinkBallsTwo.getText()))+1));
        }
        else if (view.getId() == blackBallsOnTable.getId()) {
            playerTwoScore.setText(String.valueOf(Integer.parseInt(String.valueOf(playerTwoScore.getText())) + 7));
            pockedBlackBallsTwo.setText(String.valueOf(Integer.parseInt(String.valueOf(pockedBlackBallsTwo.getText()))+1));
        }

        redBallsTurn();
    }
    public void winner(View view) {
        if(Integer.parseInt(String.valueOf(playerOneScore.getText())) > Integer.parseInt(String.valueOf(playerTwoScore.getText())))
            Toast.makeText(this, "O'Sullivan is winner", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Williams is winner", Toast.LENGTH_LONG).show();
    }
}