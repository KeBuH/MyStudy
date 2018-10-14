package ru.tretyakov.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Class of move.
 * author Rooter
 * since 23.09.18
 **/

public class RectangleMove implements Runnable {
    /**
     * Shape
     */
    private final Rectangle rect;
    private int limitX;
    private boolean straight = true;
    static boolean open = true;

    /**
     * Init constructor.
     * @param rect shape
     * @param limitX limit
     */
    public RectangleMove(final Rectangle rect, final int limitX) {
        this.rect = rect;
        this.limitX = limitX;
    }

    /**
     * Do move method.
     */
    @Override
    public void run() {
        while (open) {
            if (this.rect.getX() == limitX) {
                this.straight = false;
            } else if (this.rect.getX() == 0) {
                this.straight = true;
            }
            if (this.straight) {
                this.rect.setX(this.rect.getX() + 1);
            } else {
                this.rect.setX(this.rect.getX() - 1);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
