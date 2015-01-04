package net.xiayule.netbar.db;

import java.util.List;


public interface ComputerDao {
	public List getComputerByState(int state);
	public void update(String computerid, int state);
	public boolean isONorOFF(String computerid);
}
