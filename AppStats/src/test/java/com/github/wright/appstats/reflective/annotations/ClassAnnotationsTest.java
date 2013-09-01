package com.github.wright.appstats.reflective.annotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.wright.appstats.dummyclasses.HeadTeacher;
import com.github.wright.appstats.dummyclasses.Person;
import com.github.wright.appstats.dummyclasses.PrincipleTeacher;
import com.github.wright.appstats.dummyclasses.Teacher;
import com.github.wrightm.appstats.annotations.InvokeIntercept;

public class ClassAnnotationsTest {

	
	private static Person teacherPoly;
	private static Teacher  teacher;
	private static HeadTeacher headTeacher;
	private static PrincipleTeacher principleTeacher;
	private static ClassAnnotationInterrogator classMethodNamesAnnotated;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		teacherPoly = new Teacher();
		teacher = new HeadTeacher();
		headTeacher = new HeadTeacher();
		principleTeacher = new PrincipleTeacher();
		
		classMethodNamesAnnotated = ClassAnnotationInterrogator.createClassAnnotationInterrogator();
		classMethodNamesAnnotated.setAnnotatedClasses(InvokeIntercept.class, teacher);
		classMethodNamesAnnotated.setAnnotatedClasses(InvokeIntercept.class, teacherPoly);
		classMethodNamesAnnotated.setAnnotatedClasses(InvokeIntercept.class, headTeacher);
		classMethodNamesAnnotated.setAnnotatedClasses(InvokeIntercept.class, principleTeacher);
	}
	
	@Test 
	public void testClassAnnotationInterrogatorisClassAnnotatedTeacherPoly() {
		assertTrue(classMethodNamesAnnotated.isClassAnnotated(teacherPoly.getClass().getCanonicalName()));
	}

	@Test 
	public void testClassAnnotationInterrogatorisClassAnnotatedTeacher() {
		assertTrue(classMethodNamesAnnotated.isClassAnnotated(teacher.getClass().getCanonicalName()));
	}
	
	@Test 
	public void testClassAnnotationInterrogatorisClassAnnotatedHeadTeacher() {
		assertTrue(classMethodNamesAnnotated.isClassAnnotated(headTeacher.getClass().getCanonicalName()));
	}
	
	@Test 
	public void testClassAnnotationInterrogatorisClassAnnotatedPrincipleTeacher() {
		assertFalse(classMethodNamesAnnotated.isClassAnnotated(principleTeacher.getClass().getCanonicalName()));
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetAllAnnotatedClassIdentifiersTeacherPoly() {
		
		Map<String, Set<String> > allAnnotatedClassIdentifiers = classMethodNamesAnnotated.getAllAnnotatedClassIdentifiers();
		
		String classCanonicalName = teacherPoly.getClass().getCanonicalName();
		String identifier = classCanonicalName+":getSubject";
		
		assertThat(allAnnotatedClassIdentifiers.get(classCanonicalName), hasItems(identifier));
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetAllAnnotatedClassIdentifiersTeacher() {
		
		Map<String, Set<String> > allAnnotatedClassIdentifiers = classMethodNamesAnnotated.getAllAnnotatedClassIdentifiers();
		
		String classCanonicalName = teacher.getClass().getCanonicalName();
		String identifierGetSubject = classCanonicalName+":getSubject";
		String identifierSetName = classCanonicalName+":setName";
		String identifierGetName = classCanonicalName+":getName";
		
		Set<String> identifiers = new HashSet<String>();
		identifiers.add(identifierGetName);
		identifiers.add(identifierSetName);
		identifiers.add(identifierGetSubject);
		
 		assertThat(allAnnotatedClassIdentifiers.get(classCanonicalName), is(identifiers));
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetAllAnnotatedClassIdentifiersHeadTeacher() {
		
		Map<String, Set<String> > allAnnotatedClassIdentifiers = classMethodNamesAnnotated.getAllAnnotatedClassIdentifiers();
		
		String classCanonicalName = headTeacher.getClass().getCanonicalName();
		String identifierGetSubject = classCanonicalName+":getSubject";
		String identifierSetName = classCanonicalName+":setName";
		String identifierGetName = classCanonicalName+":getName";
		
		Set<String> identifiers = new HashSet<String>();
		identifiers.add(identifierGetName);
		identifiers.add(identifierSetName);
		identifiers.add(identifierGetSubject);
		
 		assertThat(allAnnotatedClassIdentifiers.get(classCanonicalName), is(identifiers));
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetClassIdentifiersTeacherPoly() {
		
		String classCanonicalName = teacherPoly.getClass().getCanonicalName(); 
		
		Set<String> annotatedClassIdentifiers = classMethodNamesAnnotated.getClassIdentifiers(classCanonicalName);

		String identifier = classCanonicalName+":getSubject";
		
		assertThat(annotatedClassIdentifiers, hasItems(identifier));
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetClassIdentifiersTeacher() {
		
		String classCanonicalName = teacherPoly.getClass().getCanonicalName(); 
		
		Set<String> annotatedClassIdentifiers = classMethodNamesAnnotated.getClassIdentifiers(classCanonicalName);
		
		String identifierGetSubject = classCanonicalName+":getSubject";
		
		Set<String> identifiers = new HashSet<String>();
		identifiers.add(identifierGetSubject);
		
 		assertThat(annotatedClassIdentifiers, is(identifiers));
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetClassIdentifiersHeadTeacher() {
		
		String classCanonicalName = headTeacher.getClass().getCanonicalName();
		
		Set<String> annotatedClassIdentifiers = classMethodNamesAnnotated.getClassIdentifiers(classCanonicalName);
		
		String identifierGetSubject = classCanonicalName+":getSubject";
		String identifierSetName = classCanonicalName+":setName";
		String identifierGetName = classCanonicalName+":getName";
		
		Set<String> identifiers = new HashSet<String>();
		identifiers.add(identifierGetName);
		identifiers.add(identifierSetName);
		identifiers.add(identifierGetSubject);
		
 		assertThat(annotatedClassIdentifiers, is(identifiers));
	}
	
	
	@Test 
	public void testClassAnnotationInterrogatorGetAllClassAnnotationsTeacherPoly() {
		
		Map<String, Set< Class<? extends Annotation> > > allClassAnnotations = classMethodNamesAnnotated.getAllClassAnnotations();
		
		String classCanonicalName = teacherPoly.getClass().getCanonicalName();
		String identifier = classCanonicalName+":getSubject";
		
		Set< Class<? extends Annotation> > annotations = new HashSet< Class<? extends Annotation> >();
		annotations.add(InvokeIntercept.class);
		assertThat( allClassAnnotations.get(identifier), is(annotations) );
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetAllClassAnnotationsTeacher() {
		
		Map<String, Set< Class<? extends Annotation> > > allClassAnnotations = classMethodNamesAnnotated.getAllClassAnnotations();
		
		String classCanonicalName = teacher.getClass().getCanonicalName();
		String identifier = classCanonicalName+":getSubject";
		
		Set< Class<? extends Annotation> > annotations = new HashSet< Class<? extends Annotation> >();
		annotations.add(InvokeIntercept.class);
		
		assertThat( allClassAnnotations.get(identifier), is(annotations) );
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetAllClassAnnotationsHeadTeacher() {
		
		Map<String, Set< Class<? extends Annotation> > > allClassAnnotations = classMethodNamesAnnotated.getAllClassAnnotations();
		
		String classCanonicalName = headTeacher.getClass().getCanonicalName();
		String identifier = classCanonicalName+":getSubject";
		
		Set< Class<? extends Annotation> > annotations = new HashSet< Class<? extends Annotation> >();
		annotations.add(InvokeIntercept.class);
		
		assertThat( allClassAnnotations.get(identifier), is(annotations) );
	}
	
	
	
	@Test 
	public void testClassAnnotationInterrogatorGetClassAnnotationsTeacherPoly() {
		
		String classCanonicalName = teacherPoly.getClass().getCanonicalName();
		String identifier = classCanonicalName+":getSubject";
		
		Set< Class<? extends Annotation> > allClassAnnotations = classMethodNamesAnnotated.getClassAnnotations(identifier);
		
		Set< Class<? extends Annotation> > annotations = new HashSet< Class<? extends Annotation> >();
		annotations.add(InvokeIntercept.class);
		
		assertThat( allClassAnnotations, is(annotations) );
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetClassAnnotationsTeacher() {
		
		String classCanonicalName = teacher.getClass().getCanonicalName();
		String identifier = classCanonicalName+":getSubject";
		
		Set< Class<? extends Annotation> > allClassAnnotations = classMethodNamesAnnotated.getClassAnnotations(identifier);
		
		Set< Class<? extends Annotation> > annotations = new HashSet< Class<? extends Annotation> >();
		annotations.add(InvokeIntercept.class);
		
		assertThat( allClassAnnotations, is(annotations) );
	}
	
	@Test 
	public void testClassAnnotationInterrogatorGetClassAnnotationsHeadTeacher() {
		
		String classCanonicalName = headTeacher.getClass().getCanonicalName();
		String identifier = classCanonicalName+":getSubject";
		
		Set< Class<? extends Annotation> > allClassAnnotations = classMethodNamesAnnotated.getClassAnnotations(identifier);
		
		Set< Class<? extends Annotation> > annotations = new HashSet< Class<? extends Annotation> >();
		annotations.add(InvokeIntercept.class);
		
		assertThat( allClassAnnotations, is(annotations) );
	}
	
}
