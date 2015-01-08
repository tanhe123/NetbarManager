package net.xiayule.netbar.db;

import net.xiayule.netbar.domain.ComputerRow;
import net.xiayule.netbar.entity.Computer;

import java.util.ArrayList;
import java.util.List;


public interface ComputerDao {
	public List getComputerByState(int state);
	public ArrayList<Computer> getComputer();
	public void update(Integer computerid, int state);
	public boolean isONorOFF(Integer computerid);

	/**
	 * 获取要在table中显示的行信息
	 * @return
	 */
	public List<ComputerRow> queryComputerRows();
}
