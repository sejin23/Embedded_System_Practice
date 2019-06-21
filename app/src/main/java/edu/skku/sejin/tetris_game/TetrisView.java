package edu.skku.sejin.tetris_game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class TetrisView extends View {
    static {
        System.loadLibrary("native-lib");
    }
    private final char[][][] blockMode = {
            {
                    {1, 0, 0, 0},
                    {1, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {1, 1, 0, 0},
                    {1, 0, 0, 0},
                    {1, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {1, 1, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {0, 0, 2, 0},
                    {2, 2, 2, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {2, 0, 0, 0},
                    {2, 0, 0, 0},
                    {2, 2, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {2, 2, 2, 0},
                    {2, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {2, 2, 0, 0},
                    {0, 2, 0, 0},
                    {0, 2, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {3, 3, 0, 0},
                    {0, 3, 3, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {0, 3, 0, 0},
                    {3, 3, 0, 0},
                    {3, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {3, 3, 0, 0},
                    {0, 3, 3, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {0, 3, 0, 0},
                    {3, 3, 0, 0},
                    {3, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {0, 4, 4, 0},
                    {4, 4, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {4, 0, 0, 0},
                    {4, 4, 0, 0},
                    {0, 4, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {0, 4, 4, 0},
                    {4, 4, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {4, 0, 0, 0},
                    {4, 4, 0, 0},
                    {0, 4, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {5, 5, 0, 0},
                    {5, 5, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {5, 5, 0, 0},
                    {5, 5, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {5, 5, 0, 0},
                    {5, 5, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {5, 5, 0, 0},
                    {5, 5, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {0, 6, 0, 0},
                    {6, 6, 6, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {6, 0, 0, 0},
                    {6, 6, 0, 0},
                    {6, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {6, 6, 6, 0},
                    {0, 6, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {0, 6, 0, 0},
                    {6, 6, 0, 0},
                    {0, 6, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {7, 7, 7, 7},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {7, 0, 0, 0},
                    {7, 0, 0, 0},
                    {7, 0, 0, 0},
                    {7, 0, 0, 0}    },
            {
                    {7, 7, 7, 7},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}    },
            {
                    {7, 0, 0, 0},
                    {7, 0, 0, 0},
                    {7, 0, 0, 0},
                    {7, 0, 0, 0}    },
    };

    private char[][] board = {
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0},
            {0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 0},
    };

    private Bitmap mBackBit;
    private Bitmap mBlock, mEmpty;
    private Bitmap mRotate, mArwLeft, mArwRight, mArwDown, mStart, mDouble;
    private Bitmap mBlack, mWhite, mRed, mYellow, mGreen, mBlue, mPupple;

    private Paint myFrontPaint;
    private Paint myDownPaint;

    private Canvas myCanvas;

    private Rect RotRect, LeftRect, RightRect, DownRect, StartRect, DoubleRect;

    private Context mcontext = null;

    private final static int BLOCK_SIZE = 31;
    private final static int NUM_OF_BLOCK = 7;
    private final static int BTN_SIZE = 52;
    private final static int BTN_SPACE = 30;
    private final static int RECT_SPACE = 8;
    private final static int PADDING_LEFT = 10;

    private int nVertical;
    private int myBlockX = 4, myBlockY = 0;
    private int CurrentBlock = 0;
    private int Rotate = 0;
    private int myScore = 0;
    private int Interval = 1000;
    private int onBtnX = 0, onBtnY = 0;
    private int listptr;
    private Random random = new Random();

    private char[] listblocks;
    private boolean isDown = false;
    private boolean isRunning = false;

    public TetrisView(Context context) {
        super(context);
        this.mcontext = context;
        initItem();
    }

    public TetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mcontext = context;
        initItem();
    }

    public TetrisView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        this.mcontext = context;
        initItem();
    }

    private void initItem() {
        int nWidth = getResources().getDisplayMetrics().widthPixels;
        int nHeight = getResources().getDisplayMetrics().heightPixels;

        nVertical = BLOCK_SIZE * 21 + 2;

        mBackBit = Bitmap.createBitmap(nWidth, nHeight, Bitmap.Config.ARGB_8888);
        mBlock = BitmapFactory.decodeResource(getResources(), R.drawable.block);
        mEmpty = BitmapFactory.decodeResource(getResources(), R.drawable.empty);
        mBlack = BitmapFactory.decodeResource(getResources(), R.drawable.black);
        mWhite = BitmapFactory.decodeResource(getResources(), R.drawable.white);
        mRed = BitmapFactory.decodeResource(getResources(), R.drawable.red);
        mYellow = BitmapFactory.decodeResource(getResources(), R.drawable.yellow);
        mGreen = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        mBlue = BitmapFactory.decodeResource(getResources(), R.drawable.blue);
        mPupple = BitmapFactory.decodeResource(getResources(), R.drawable.pupple);

        mStart = BitmapFactory.decodeResource(getResources(), R.drawable.start);
        mArwLeft = BitmapFactory.decodeResource(getResources(), R.drawable.arwleft);
        mArwRight = BitmapFactory.decodeResource(getResources(), R.drawable.arwright);
        mArwDown = BitmapFactory.decodeResource(getResources(), R.drawable.arwdown);
        mDouble = BitmapFactory.decodeResource(getResources(), R.drawable.doublearrow);
        mRotate = BitmapFactory.decodeResource(getResources(), R.drawable.rotate);

        myFrontPaint = new Paint();
        myDownPaint = new Paint();
        myDownPaint.setColor(Color.BLUE);

        myCanvas = new Canvas(mBackBit);

        StartRect = new Rect(PADDING_LEFT, nVertical, PADDING_LEFT + BTN_SIZE + RECT_SPACE, nVertical + BTN_SIZE + RECT_SPACE);
        LeftRect = new Rect(PADDING_LEFT + BTN_SIZE + BTN_SPACE, nVertical, PADDING_LEFT + 2 * BTN_SIZE + BTN_SPACE + RECT_SPACE, nVertical + BTN_SIZE + RECT_SPACE);
        DownRect = new Rect(PADDING_LEFT + 2 * (BTN_SIZE + BTN_SPACE), nVertical, PADDING_LEFT + 3 * BTN_SIZE +  2 * BTN_SPACE + RECT_SPACE, nVertical + BTN_SIZE + RECT_SPACE);
        DoubleRect = new Rect(PADDING_LEFT + 3 * (BTN_SIZE + BTN_SPACE), nVertical, PADDING_LEFT + 4 * BTN_SIZE + 3 * BTN_SPACE + RECT_SPACE, nVertical + BTN_SIZE + RECT_SPACE);
        RightRect = new Rect(PADDING_LEFT + 4 * (BTN_SIZE + BTN_SPACE), nVertical, PADDING_LEFT + 5 * BTN_SIZE + 4 * BTN_SPACE + RECT_SPACE, nVertical + BTN_SIZE + RECT_SPACE);
        RotRect = new Rect(PADDING_LEFT + 5 * (BTN_SIZE + BTN_SPACE), nVertical, PADDING_LEFT + 6 * BTN_SIZE + 5 * BTN_SPACE + RECT_SPACE, nVertical + BTN_SIZE + RECT_SPACE);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        drawBackground(canvas);
        drawBlock(canvas);
    }

    private void drawBlock(Canvas canvas) {
        for(int j = 0; j < board.length ; j++) {
            for(int i = 0; i < board[j].length; i++) {
                switch (board[j][i]) {
                    case 0:
                        canvas.drawBitmap(mEmpty, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                    case 1:
                        canvas.drawBitmap(mBlack, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                    case 2:
                        canvas.drawBitmap(mWhite, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                    case 3:
                        canvas.drawBitmap(mRed, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                    case 4:
                        canvas.drawBitmap(mYellow, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                    case 5:
                        canvas.drawBitmap(mGreen, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                    case 6:
                        canvas.drawBitmap(mBlue, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                    case 7:
                        canvas.drawBitmap(mPupple, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                    case 9:
                        canvas.drawBitmap(mBlock, i * BLOCK_SIZE, j * BLOCK_SIZE, null);
                        break;
                }
            }
        }

        if(!isRunning) return;

        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 4; i++) {
                switch (blockMode[CurrentBlock + Rotate][j][i]) {
                    case 1:
                        canvas.drawBitmap(mBlack, myBlockX * BLOCK_SIZE + i * BLOCK_SIZE, myBlockY * BLOCK_SIZE + j * BLOCK_SIZE, null);
                        break;
                    case 2:
                        canvas.drawBitmap(mWhite, myBlockX * BLOCK_SIZE + i * BLOCK_SIZE, myBlockY * BLOCK_SIZE + j * BLOCK_SIZE, null);
                        break;
                    case 3:
                        canvas.drawBitmap(mRed, myBlockX * BLOCK_SIZE + i * BLOCK_SIZE, myBlockY * BLOCK_SIZE + j * BLOCK_SIZE, null);
                        break;
                    case 4:
                        canvas.drawBitmap(mYellow, myBlockX * BLOCK_SIZE + i * BLOCK_SIZE, myBlockY * BLOCK_SIZE + j * BLOCK_SIZE, null);
                        break;
                    case 5:
                        canvas.drawBitmap(mGreen, myBlockX * BLOCK_SIZE + i * BLOCK_SIZE, myBlockY * BLOCK_SIZE + j * BLOCK_SIZE, null);
                        break;
                    case 6:
                        canvas.drawBitmap(mBlue, myBlockX * BLOCK_SIZE + i * BLOCK_SIZE, myBlockY * BLOCK_SIZE + j * BLOCK_SIZE, null);
                        break;
                    case 7:
                        canvas.drawBitmap(mPupple, myBlockX * BLOCK_SIZE + i * BLOCK_SIZE, myBlockY * BLOCK_SIZE + j * BLOCK_SIZE, null);
                        break;
                }
            }
        }
    }

    private void drawBackground(Canvas canvas) {
        myFrontPaint.setAntiAlias(true);

        myCanvas.drawColor(Color.WHITE);

        if(isDown && StartRect.contains(onBtnX, onBtnY))
            myCanvas.drawRect(StartRect, myDownPaint);
        else {
            myFrontPaint.setColor(Color.TRANSPARENT);
            myCanvas.drawRect(StartRect, myFrontPaint);
        }
        if(isDown && LeftRect.contains(onBtnX, onBtnY))
            myCanvas.drawRect(LeftRect, myDownPaint);
        else {
            myFrontPaint.setColor(Color.TRANSPARENT);
            myCanvas.drawRect(LeftRect, myFrontPaint);
        }
        if(isDown && DownRect.contains(onBtnX, onBtnY))
            myCanvas.drawRect(DownRect, myDownPaint);
        else {
            myFrontPaint.setColor(Color.TRANSPARENT);
            myCanvas.drawRect(DownRect, myFrontPaint);
        }
        if(isDown && DoubleRect.contains(onBtnX, onBtnY))
            myCanvas.drawRect(DoubleRect, myDownPaint);
        else {
            myFrontPaint.setColor(Color.TRANSPARENT);
            myCanvas.drawRect(DoubleRect, myFrontPaint);
        }
        if(isDown && RightRect.contains(onBtnX, onBtnY))
            myCanvas.drawRect(RightRect, myDownPaint);
        else {
            myFrontPaint.setColor(Color.TRANSPARENT);
            myCanvas.drawRect(RightRect, myFrontPaint);
        }
        if(isDown && RotRect.contains(onBtnX, onBtnY))
            myCanvas.drawRect(RotRect, myDownPaint);
        else {
            myFrontPaint.setColor(Color.TRANSPARENT);
            myCanvas.drawRect(RotRect, myFrontPaint);
        }
        myCanvas.drawBitmap(mStart, PADDING_LEFT, nVertical, null);
        myCanvas.drawBitmap(mArwLeft, PADDING_LEFT + BTN_SIZE + BTN_SPACE, nVertical, null);
        myCanvas.drawBitmap(mArwDown, PADDING_LEFT + 2 * (BTN_SIZE + BTN_SPACE), nVertical, null);
        myCanvas.drawBitmap(mDouble, PADDING_LEFT + 3 * (BTN_SIZE + BTN_SPACE), nVertical, null);
        myCanvas.drawBitmap(mArwRight, PADDING_LEFT + 4 * (BTN_SIZE + BTN_SPACE), nVertical, null);
        myCanvas.drawBitmap(mRotate, PADDING_LEFT + 5 * (BTN_SIZE + BTN_SPACE), nVertical, null);

        canvas.drawBitmap(mBackBit, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int dx = (int)event.getX();
        int dy = (int)event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            onBtnX = dx;
            onBtnY = dy;
            isDown = true;
        } else {
            isDown = false;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (StartRect.contains(dx, dy)) {
                    if (isRunning) {
                        initBoard();
                        myBlockX = 4;
                        myBlockY = 0;
                        isRunning = false;
                        myScore = 0;
                    } else {
                        myScore = 0;
                        initBoard();
                        initListBlock();
                        initNewBlock();
                        sendScoretoJNI(0);
                        isRunning = true;
                        myHandler.sendEmptyMessageDelayed(0, Interval);
                    }
                } else if (LeftRect.contains(dx, dy) && isRunning)
                    shiftBlock(myBlockX - 1, myBlockY, Rotate);
                else if (DownRect.contains(dx, dy) && isRunning)
                    downBlock();
                else if (DoubleRect.contains(dx, dy) && isRunning)
                    fallBlock();
                else if (RightRect.contains(dx, dy) && isRunning)
                    shiftBlock(myBlockX + 1, myBlockY, Rotate);
                else if (RotRect.contains(dx, dy) && isRunning)
                    rotateBlock();
            }
        }
        invalidate();
        return true;
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(isRunning) {
                downBlock();
                invalidate();
                myHandler.sendEmptyMessageDelayed(0, Interval);
            }
        }
    };

    private int nextBlock() {
        int nextb = listblocks[listptr];
        int num;
        char[] element = {0, 0, 0, 0, 0, 0, 0};
        listptr = (listptr + 1) % 14;
        if(listptr % 7 == 0) {
            for(int i = 0; i < NUM_OF_BLOCK; i++){
                num = random.nextInt(NUM_OF_BLOCK);
                while(element[num] == 1) num = random.nextInt(NUM_OF_BLOCK);
                if(listptr / 7 == 1) listblocks[i] = (char)num;
                else listblocks[NUM_OF_BLOCK + i] = (char)num;
            }
        }
        for(int i = 0; i < NUM_OF_BLOCK; i++)
            element[i] = listblocks[(listptr + i) % 14];
        setnextledtoJNI(element);
        return nextb * 4;
    }

    private boolean isCollision(int x, int y, int rot) {
        if(x < 1 || x >= board[0].length - 1) return true;
        if(y < 0 || y >= board.length - 1) return true;

        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 4; i++) {
                if(blockMode[CurrentBlock + rot][j][i] > 0 && board[y + j][x + i] > 0)
                    return true;
            }
        }
        return false;
    }

    private void shiftBlock(int x, int y, int rot) {
        if(isCollision(x, y, rot)) return;

        myBlockX = x;
        myBlockY = y;
        Rotate = rot;
    }

    private void downBlock() {
        if(isCollision(myBlockX, myBlockY + 1, Rotate)) {
            saveBlock();
            saveScore();
            initNewBlock();
            if(isGameOver()) {
                gameOver();
            }
            return;
        }
        myBlockY += 1;
        return;
    }

    private void fallBlock() {
        int i = 0;
        while (!isCollision(myBlockX, myBlockY + i, Rotate)) i++;

        myBlockY += i - 1;

        saveBlock();
        saveScore();
        initNewBlock();
        if(isGameOver())
            gameOver();
        return;
    }

    private void initBoard() {
        for(int j = 0; j < board.length - 1; j++) {
            for(int i = 3; i < board[j].length - 3; i++)
                board[j][i] = 0;
        }
    }

    private boolean isGameOver() {
        if(isCollision(myBlockX, myBlockY, Rotate)) return true;
        else return false;
    }

    private void saveBlock() {
        for(int j = 0; j < 4; j++) {
            for(int i = 0; i < 4; i++) {
                if(blockMode[CurrentBlock + Rotate][j][i] > 0)
                    board[myBlockY + j][myBlockX + i] = blockMode[CurrentBlock + Rotate][j][i];
            }
        }
    }

    private void saveScore() {
        int temp = 0;
        for(int j = board.length - 2; j >= 0; j--) {
            boolean isCleared = true;
            for(int i = 3; i < board[j].length - 3; i++) {
                if(board[j][i] == 0) {
                    isCleared = false;
                    break;
                }
            }

            if(isCleared) {
                temp++;
                if(j > 0) {
                    for(int k = j; k > 0; k--) {
                        for(int l = 0; l < board[k].length - 3; l++)
                            System.arraycopy(board[k - 1], 1, board[k], 1, board[k].length - 2);
                    }
                    j++;
                }
            }
        }
        if(temp > 0) {
            myScore += temp * temp * 10;
            sendScoretoJNI(myScore);
        }
    }

    private void rotateBlock() {
        int newRotate = (Rotate + 1) % 4;

        if(isCollision(myBlockX, myBlockY, newRotate)) return;
        Rotate = newRotate;
    }

    private void initListBlock() {
        listblocks = new char[14];
        char[] element = {1, 1, 1, 1, 1, 1, 1};
        int num;
        listptr = 0;
        for(int j = 0; j < 2; j++) {
            for (int i = 0; i < NUM_OF_BLOCK; i++) {
                num = random.nextInt(NUM_OF_BLOCK);
                while(element[num] == (char)j) num = random.nextInt(NUM_OF_BLOCK);
                listblocks[j * NUM_OF_BLOCK + i] = (char)num;
                element[num] = (char)j;
            }
        }
    }

    private void initNewBlock() {
        myBlockX = 4;
        myBlockY = 0;

        CurrentBlock = nextBlock();
    }

    private void gameOver() {
        myBlockX = 4;
        myBlockY = 0;
        isRunning = false;

        ((GameMain)mcontext).gotoIntent(myScore);
    }

    public native void sendScoretoJNI(int score);
    public native void setnextledtoJNI(char[] lists);
}
