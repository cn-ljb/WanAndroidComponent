package com.ljb.android.apt.compiler;

import com.google.auto.service.AutoService;
import com.ljb.android.apt.annotation.InitComponent;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.ljb.android.apt.annotation.InitComponent")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions("initPath")
public class InitComponentProcessor  extends AbstractProcessor {

    private Elements elementUtils;
    private Types typeUtils;
    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        typeUtils = processingEnvironment.getTypeUtils();
        messager = processingEnvironment.getMessager();
        filer =  processingEnvironment.getFiler();

        String  path  = processingEnvironment.getOptions().get("initPath");
        messager.printMessage(Diagnostic.Kind.NOTE , "InitComponent: >>>>>> " + path );
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if(set.isEmpty()) return false;
        //获取所有使用InitComponent注解的类
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(InitComponent.class);
        //遍历所有类节点
        for(Element e : elements){
            // 包名
            String packageName = elementUtils.getPackageOf(e).getQualifiedName().toString();
            // 类名
            String className = e.getSimpleName().toString();
        }
        return true;
    }
}
