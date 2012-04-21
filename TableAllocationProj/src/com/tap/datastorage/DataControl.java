package com.tap.datastorage;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class DataControl {
	public static ObjectContainer getOCDB(){
		return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "auto.db");
	}
}
