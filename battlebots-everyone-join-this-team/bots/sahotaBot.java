package bots;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;


import arena.BattleBotArena;
import arena.BotInfo;
import arena.Bullet;




public class sahotabot extends Bot {
    private double px;
    private double py;
    private String msg = null;
    private String nextMessage = null;
    private int move = BattleBotArena.UP;
    private String[] killMessages = {"HAHAHA", "U TRASH!", "noob ong", "YO JEEJ", "BOOM!", "uninstall the game rn", "my grandma better bro"};
    private int msgCounter = 0;
    private boolean trashtalk = true;
    private int stuck = 0;
    private int sticking = 0;
    private int deadnumber = 0;
    Image up, down, left, right, current;




    @Override
    public void newRound() {
        msg = "LETS GET THIS DUB!";
        msgCounter = 100;
    }




    @Override
    public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets) {
        // TODO Auto-generated method stub
        // Dodging
        if(deadBots.length > deadnumber){
            deadnumber = deadBots.length;
            msgCounter = 100;
        }
        if((me.getX() == px && me.getY() == py)){
            sticking += 1;
        }
        else{
            sticking = 0;
        }
        if(sticking > 5 || stuck > 0){
            move = unstuck(me, liveBots, deadBots);
            px = me.getX();
            py = me.getY();
            return move;
        }
        px = me.getX();
        py = me.getY();
        move = dodge(me, bullets);
        if(move != BattleBotArena.SEND_MESSAGE){
            return move;
        }
        if(shotOK){
            move = shoot(me, liveBots);
        }
        if(move != BattleBotArena.SEND_MESSAGE){
            return move;
        }
        if(msgCounter > 0){
            move = BattleBotArena.SEND_MESSAGE;
            return move;
        }
        move = hunt(me, liveBots);
            return move;
        }
       
    public int unstuck(BotInfo me, BotInfo[] liveBots, BotInfo[] deadBots){
        if(stuck == 0){
            stuck = 5;
            Random rand = new Random();
            int direction = rand.nextInt(4);
            if(direction == 0){
                move = BattleBotArena.DOWN;
            }
            else if(direction == 1){
                move = BattleBotArena.UP;
            }
            else if(direction == 2){
                move = BattleBotArena.LEFT;
            }
            else if (direction == 3){
                move = BattleBotArena.RIGHT;
            }
        }
        stuck = stuck - 1;
        return move;
    }
    public int shoot(BotInfo me,  BotInfo[] liveBots){
        for (int i = 0;i < liveBots.length;  i++) {
        double x = liveBots[i].getX();
        double y = liveBots[i].getY();
        if(x == me.getX() && y == me.getY() && liveBots.length > i + 1){
             x = liveBots[i + 1].getX();
             y = liveBots[i + 1].getY();
        }
        double ydiff = y - me.getY();
        double xdiff = x - me.getX();
        if (-10 < xdiff && xdiff < 10)
            {
                if(y < me.getY()){
                    move = BattleBotArena.FIREUP;
                    return move;
                }
                if(y > me.getY()){
                    move = BattleBotArena.FIREDOWN;
                    return move;
                }
               
            }
        if (-10 < ydiff && ydiff < 10)
            {
                if(x < me.getX()){
                    move = BattleBotArena.FIRELEFT;
                    return move;
                }
                if(x > me.getX()){
                    move = BattleBotArena.FIRERIGHT;
                    return move;
                }
               
            }
        }
       
        return BattleBotArena.SEND_MESSAGE;
    }  




    public int hunt(BotInfo me, BotInfo[] liveBots){
        double x = liveBots[0].getX();
        double y = liveBots[0].getY();
        if(x == me.getX() && y == me.getY() && liveBots.length > 1){
             x = liveBots[1].getX();
             y = liveBots[1].getY();
        }
        Random rand = new Random();
        if(rand.nextInt(2)== 1) {
            if(y < me.getY()){
                move = BattleBotArena.UP;
                return move;
            }
            if(y > me.getY()){
                move = BattleBotArena.DOWN;
                return move;
            }
            if(x < me.getX()){
                move = BattleBotArena.LEFT;
                return move;
            }
            if(x > me.getX()){
                move = BattleBotArena.RIGHT;
                return move;
            }
        }
        else {
            if(x < me.getX()){
                move = BattleBotArena.LEFT;
                return move;
            }
            if(x > me.getX()){
                move = BattleBotArena.RIGHT;
                return move;
            }
            if(y < me.getY()){
                move = BattleBotArena.UP;
                return move;
            }
            if(y > me.getY()){
                move = BattleBotArena.DOWN;
                return move;
            }
        }
        return BattleBotArena.SEND_MESSAGE;
       
    }




    public int dodge(BotInfo me, Bullet[] bullets) {




        move = BattleBotArena.SEND_MESSAGE;
        for(int i = 0;i < bullets.length;  i++){
            double x = bullets[i].getX();
            double y = bullets[i].getY();
            double xspeed = bullets[i].getXSpeed();
            double yspeed = bullets[i].getYSpeed();
            double ydiff = y - me.getY() - 13;
            double xdiff = x - me.getX() - 13;
            double space =  21;
            if (xspeed > 0)
            {
                if (xdiff < 0){
                    if (ydiff < space && ydiff > 0){
                        move = BattleBotArena.UP;
                    }
                    else if (ydiff > -space && ydiff <= 0){
                        move = BattleBotArena.DOWN;
                    }
                }
               
            }
            else if (xspeed < 0)
            {
                if (xdiff > 0){
                    if (ydiff < space && ydiff > 0){
                        move = BattleBotArena.UP;
                    }
                    else if (ydiff > -space && ydiff <= 0){
                        move = BattleBotArena.DOWN;
                    }
                }
               
            }
            else if (yspeed < 0)
            {
                if (ydiff > 0){
                    if (xdiff < space && xdiff > 0){
                        move = BattleBotArena.LEFT;
                    }
                    else if (xdiff > -space && xdiff <= 0){
                        move = BattleBotArena.RIGHT;
                    }
                }
               
            }
            else if (yspeed > 0)
            {
                if (ydiff < 0){
                    if (xdiff < space && xdiff > 0){
                        move = BattleBotArena.LEFT;
                    }
                    else if (xdiff > -space && xdiff <= 0){
                        move = BattleBotArena.RIGHT;
                    }
                }
               
            }
        }
            return move;    
    }
   
   
    @Override
    public void draw(Graphics g, int x, int y) {
        // TODO Auto-generated method stub
        g.drawImage(current, x, y, Bot.RADIUS*2, Bot.RADIUS*2, null);
    }




    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "shivmcgriv";
    }




    @Override
    public String getTeamName() {
        // TODO Auto-generated method stub
        return "Sahota";
    }




    @Override
    public String outgoingMessage() {
        // TODO Auto-generated method stub
        String x = msg;
        msgCounter = msgCounter - 1;
        trashtalk = false;
        return msg;
    }




    @Override
    public void incomingMessage(int botNum, String msgln) {
        if (msgln.matches(msg))
        {
            int msgNum = (int)(Math.random()*killMessages.length);
            msg = killMessages[msgNum];
            msgCounter = 0;
        }




    }




    @Override
    public String[] imageNames() {
        String[] images = {"pikachu_up.png","pikachu_down.png","pikachu_left.png","pikachu_right.png"};
        return images;
    }




    @Override
    public void loadedImages(Image[] images) {
        if (images != null)
        {
            current = up = images[0];
            down = images[1];
            left = images[2];
            right = images[3];
        }
       
    }
   
}

