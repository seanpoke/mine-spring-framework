package com.test.scan.util;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class InterFaceFilter extends AbstractTypeHierarchyTraversingFilter implements TypeFilter {

	protected InterFaceFilter(boolean considerInherited, boolean considerInterfaces) {
		super(considerInherited, considerInterfaces);
	}



//	@Override
//	protected Boolean matchInterface(String interfaceName) {
//		if (interfaceName.equals("com.test.scan.bean.D")){
//			return true;
//		}
//		return null;
//	}
}
