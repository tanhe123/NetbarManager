package net.xiayule.netbar.db;

import java.util.List;


public interface ComputerDao {
	public List getComputerByState(int state);
	public void update(Integer computerid, int state);
	public boolean isONorOFF(Integer computerid);
}
