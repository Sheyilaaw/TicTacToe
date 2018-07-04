package sheyi.tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer;
    Button block1;
    Button block2;
    Button block3;
    Button block4;
    Button block5;
    Button block6;
    Button block7;
    Button block8;
    Button block9;
    boolean gameIsOn = true;
    int[] gameState = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
    LinearLayout layoutrow1;
    LinearLayout layoutrow2;
    LinearLayout layoutrow3;
    Button restart;
    TextView result;
    int[][] winningPos = new int[][]{
            new int[]{0, 1, 2}, new int[]{0, 4, 8},
            new int[]{3, 4, 5}, new int[]{6, 7, 8},
            new int[]{0, 3, 6}, new int[]{1, 4, 7},
            new int[]{2, 5, 8}, new int[]{2, 4, 6}
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutrow1 = (LinearLayout) findViewById(R.id.row1);
        layoutrow2 = (LinearLayout) findViewById(R.id.row2);
        layoutrow3 = (LinearLayout) findViewById(R.id.row3);
        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);
        activePlayer = 0;
        restart.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (restart.getText().toString().equals("Restart Game?")) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case -1:
                                    playAgain();
                                    resetAllBtn();
                                    gameIsOn = true;
                                    return;
                                default:
                            }
                        }
                    };
                    new Builder(view.getContext()).setMessage("Do you want to restart game?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
                    restart.setText("Restart Game?");
                }else{
                    playAgain();
                    resetAllBtn();
                    gameIsOn = true;
                }
            }
        });
    }

    private void playAgain() {
        layoutrow1.setVisibility(View.VISIBLE);
        layoutrow2.setVisibility(View.VISIBLE);
        layoutrow3.setVisibility(View.VISIBLE);
        restart.setText("Start New Game?");
        this.activePlayer = 0;
        for (int i = 0; i < this.gameState.length; i++) {
            this.gameState[i] = 2;
        }
    }

    private void resetAllBtn() {
        this.block1.setText("");
        this.block2.setText("");
        this.block3.setText("");
        this.block4.setText("");
        this.block5.setText("");
        this.block6.setText("");
        this.block7.setText("");
        this.block8.setText("");
        this.block9.setText("");
        this.result.setText("");
    }

    public void choose(View view) {
        Button btn = (Button) view;
        int tappedBtn = Integer.parseInt(btn.getTag().toString());
        if (this.gameState[tappedBtn] == 2 && this.gameIsOn) {
            this.gameState[tappedBtn] = this.activePlayer;
            if (this.activePlayer == 0) {
                btn.setText("O");
                this.activePlayer = 1;
            } else {
                btn.setText("X");
                this.activePlayer = 0;
            }
            for (int[] winningPosition : this.winningPos) {
                if (this.gameState[winningPosition[0]] == this.gameState[winningPosition[1]] && this.gameState[winningPosition[1]] == this.gameState[winningPosition[2]] && this.gameState[winningPosition[0]] != 2) {
                    this.gameIsOn = false;
                    if (this.gameState[winningPosition[0]] == 0) {
                        this.result.setText("Player 1 wins");
                    } else {
                        this.result.setText("Player 2 wins");
                    }
                    this.restart.setText("Restart Game?");
                } else {
                    boolean isOver = true;
                    for (int state : this.gameState) {
                        if (state == 2) {
                            isOver = false;
                        }
                    }
                    if (isOver) {
                        this.result.setText("It's a Tie");
                    }
                }
            }
        }
    }
}