package com.tap.datastorage;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

public class DataControl {
	public static ObjectContainer getOCDB(){
		ObjectContainer oc = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "auto.db");
		return oc;
	}
	public static ObjectContainer getOCDB(Class c){
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().objectClass(c).cascadeOnUpdate(true);
		config.common().objectClass(c).cascadeOnDelete(true);
		config.common().objectClass(c).cascadeOnActivate(true);
		ObjectContainer oc = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "auto.db");
		return oc;
	}
}
