package com.github.wrightm.appstats.reflective.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClassAnnotationInterrogator {
	
	private static ClassAnnotationInterrogator classAnnotationInterrogator = null;
	private Map<String, Set<String> > annotatedClassMethodIdentifiers;
	private Map<String, Set< Class<? extends Annotation> > > classMethodAnnotations;
	
	private ClassAnnotationInterrogator() {
		annotatedClassMethodIdentifiers = new ConcurrentHashMap<String, Set<String> >();
		classMethodAnnotations = new ConcurrentHashMap<String, Set<Class<? extends Annotation> > >();
	}
	
	public static ClassAnnotationInterrogator createClassAnnotationInterrogator() {
		if(classAnnotationInterrogator == null) {
			classAnnotationInterrogator = new ClassAnnotationInterrogator(); 
		}
		return classAnnotationInterrogator;
	}
	
	public synchronized void setAnnotatedClasses(Class<? extends Annotation> annotation, Object object) {
		if(!annotatedClassMethodIdentifiers.containsKey(object.getClass().getCanonicalName())) {
			if(isClassAnnotationPresent(annotation, object.getClass())) {
				setAllClassMethodReflectiveData(annotation, object);
			} else {	
				setAnnotatedMethodNames(annotation, object);
			}
		}
	}
	
	public Map<String, Set<String> > getAllAnnotatedClassIdentifiers() {
		return annotatedClassMethodIdentifiers;
	}
	
	public Set<String> getClassIdentifiers(String objectName) {
		return annotatedClassMethodIdentifiers.get(objectName);
	}
	
	public Map<String, Set< Class<? extends Annotation> > > getAllClassAnnotations() {
		return classMethodAnnotations;
	}
	
	public Set< Class<? extends Annotation> > getClassAnnotations(String identifier) {
		return classMethodAnnotations.get(identifier);
	}
	
	public boolean isClassAnnotated(String objectName) {
		return annotatedClassMethodIdentifiers.containsKey(objectName);
	}
	
	private boolean isClassAnnotationPresent( Class<? extends Annotation> annotation, Class<? extends Object> clazz) {
		return clazz.isAnnotationPresent(annotation);
	}
	
	private void setAllClassMethodReflectiveData(Class<? extends Annotation> annotation,  Object object) {
		String classCanonicalName = object.getClass().getCanonicalName();
		for(Method method: object.getClass().getMethods()) {
			if(method.getDeclaringClass().getName().equals(Object.class.getName())) {
				continue;
			}
			String identifier = classCanonicalName+":"+method.getName();
			
			if(!annotatedClassMethodIdentifiers.containsKey(classCanonicalName)) {
				annotatedClassMethodIdentifiers.put(classCanonicalName, new HashSet<String>() );
			}
			annotatedClassMethodIdentifiers.get(classCanonicalName).add(identifier);
			
			if(!classMethodAnnotations.containsKey(identifier)) {
				classMethodAnnotations.put(identifier, new HashSet<Class<? extends Annotation> >() );
			}
			classMethodAnnotations.get(identifier).add(annotation);
		}
	}
	
	private void setAnnotatedMethodNames(Class<? extends Annotation> annotation,  Object object) {
		String classCanonicalName = object.getClass().getCanonicalName();
		for(Method method: object.getClass().getMethods()) {
			if(method.getDeclaringClass().getName().equals(Object.class.getName())) {
				continue;
			}
			if(method.isAnnotationPresent(annotation)) {
				
				String identifier = classCanonicalName+":"+method.getName();
				
				if(!annotatedClassMethodIdentifiers.containsKey(classCanonicalName)) {
					annotatedClassMethodIdentifiers.put(classCanonicalName, new HashSet<String>() );
				}
				annotatedClassMethodIdentifiers.get(classCanonicalName).add(identifier);
				
				if(!classMethodAnnotations.containsKey(identifier)) {
					classMethodAnnotations.put(identifier, new HashSet<Class<? extends Annotation> >() );
				}
				classMethodAnnotations.get(identifier).add(annotation);
			}
		}
	} 
	
}
