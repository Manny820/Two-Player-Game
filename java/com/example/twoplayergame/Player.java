package com.example.twoplayergame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

public class Player {
    protected ImageView model;
    protected ImageView health;
    protected Drawable left, right, front;
    protected float playerX, playerY;
    protected boolean actionLeft, actionRight;
    public int score, hits;
    public Player(ImageView model, ImageView health, Drawable left, Drawable right, Drawable front) {
        this.model = model;
        this.health = health;
        this.left = left;
        this.right = right;
        this.front = front;
        this.score = 0;
        this.hits = 0;
    }
    public ImageView getModel() {
        return model;
    }
    public void setModel(ImageView model) {
        this.model = model;
    }

    public ImageView getHealth() { return health; }
    public void setHealth(ImageView health) { this.health = health; }

    public Drawable getLeft() {
        return left;
    }
    public void setLeft(Drawable left) {
        this.left = left;
    }

    public Drawable getRight() {
        return right;
    }
    public void setRight(Drawable right) {
        this.right = right;
    }

    public Drawable getFront() {
        return front;
    }
    public void setFront(Drawable front) {
        this.front = front;
    }

    public float getPlayerX() {
        return playerX;
    }
    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    public float getPlayerY() { return playerY; }
    public void setPlayerY(float playerY) { this.playerY = playerY; }

    public boolean isActionLeft() {
        return actionLeft;
    }
    public void setActionLeft(boolean actionLeft) {
        this.actionLeft = actionLeft;
    }

    public boolean isActionRight() {
        return actionRight;
    }
    public void setActionRight(boolean actionRight) {
        this.actionRight = actionRight;
    }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getHits() { return hits; }
    public void setHits(int hits) { this.hits = hits; }
}
