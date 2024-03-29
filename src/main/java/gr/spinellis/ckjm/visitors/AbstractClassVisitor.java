package gr.spinellis.ckjm.visitors;

import gr.spinellis.ckjm.ClassMetrics;
import gr.spinellis.ckjm.IClassMetricsContainer;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.ConstantPoolGen;

/**
 *
 * @author marian
 */
public abstract class AbstractClassVisitor extends EmptyVisitor{

    protected IClassMetricsContainer mMetricsContainer;
    protected ConstantPoolGen mPoolGen;
    protected ClassMetrics mClassMetrics;
    protected String mClassName;

    public AbstractClassVisitor( IClassMetricsContainer metricsContainer){
        mMetricsContainer = metricsContainer;
    }

    protected void generateClassContext(JavaClass jc){
        mPoolGen = new ConstantPoolGen( jc.getConstantPool() );
        mClassName = jc.getClassName();
        mClassMetrics = mMetricsContainer.getMetrics( mClassName );
    }

    @Override
    public final void visitJavaClass(JavaClass jc) {
        generateClassContext( jc );
        visitJavaClass_body( jc );
    }
    
    protected abstract void visitJavaClass_body( JavaClass jc );
}
