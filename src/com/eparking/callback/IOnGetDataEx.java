package com.eparking.callback;

import com.eparking.data.PlateResult;
import com.sun.jna.Callback;

public interface IOnGetDataEx extends Callback
{
	public void callback(PlateResult.ByReference plateResult);
}
