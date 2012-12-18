package gr.spinellis.ckjm;

/**
 *
 * @author marian
 */
public interface ICountingProperties
{
    /** Return true if the measurements should include calls to the Java JDK into account */
    public boolean isJdkIncluded();

    /** Return true if the measurements should include all classes */
    public boolean includeAll();
}
