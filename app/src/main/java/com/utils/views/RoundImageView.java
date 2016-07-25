package com.utils.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;

public class RoundImageView
  extends NetworkImageView
{
  private final RectF rectF = new RectF();
  private float radius = 6.0F;
  private final Paint paintA = new Paint();
  private final Paint paintB = new Paint();
  
  public RoundImageView(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public RoundImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  private void init()
  {
    this.paintA.setAntiAlias(true);
    this.paintA.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    this.paintB.setAntiAlias(true);
    this.paintB.setColor(-1);
    this.radius = (getResources().getDisplayMetrics().density * this.radius);
  }
  
  public void draw(Canvas paramCanvas)
  {
    paramCanvas.saveLayer(this.rectF, this.paintB, 31);
    paramCanvas.drawRoundRect(this.rectF, this.radius, this.radius, this.paintB);
    paramCanvas.saveLayer(this.rectF, this.paintA, 31);
    super.draw(paramCanvas);
    paramCanvas.restore();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    paramInt1 = getWidth();
    paramInt2 = getHeight();
    this.rectF.set(0.0F, 0.0F, paramInt1, paramInt2);
  }
  
  public void setRectRadius(float paramFloat)
  {
    this.radius = paramFloat;
    invalidate();
  }
}