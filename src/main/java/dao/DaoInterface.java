package dao;

import java.util.ArrayList;

public interface DaoInterface<T> {

	public ArrayList<T> selectAll();
	public T selectByID(String id );
	public int insert(T t);
	public int insertAll(ArrayList<T> list);
	public int remove(T t);
	public int removeAll(ArrayList<T> list);
	public int update(T t);
}