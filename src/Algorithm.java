
public abstract class Algorithm {
	
	public abstract int[] solve(World w);
	
	@Override
	public String toString (){
		return this.getClass ().getSimpleName ();
	}
	
}
