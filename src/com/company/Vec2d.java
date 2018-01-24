package com.company;
public class Vec2d 
{
    public int x;
    public int y;
    public Vec2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Vec2d copy()
    {
        return new Vec2d(x, y);
    }
}
