/****************************************************
 * @name:   Rui Zhang
 * @email:  ruz020@eng.ucsd.edu
 ****************************************************/
public class IntArray11
{
	private int[] userVals; // an array store int values
	
	/** 
 	 * 0-argument constructor. Valid instance of an Array of int,
 	 * no ints are stored in the array.
	*/
	public IntArray11()
	{
		userVals = new int[0]; // the size of the array is 0 now. 
	}
    
	/** 
 	 * Store an array of size n. Initialize contents of the array to 
 	 * be 1..n 
 	 * @param size the number of elements to store in the array
	*/
	public IntArray11(int size)  
	{
		userVals = new int[size];  // an array size is "size"
		for (int i = 0;i < size;i++){
			userVals[i] = i + 1;
		}
	}
	
	/** 
 	 * Create an array of size n and store a copy of the contents of the
 	 * input argument
 	 * @param intArray array of elements to copy 
	*/
	public IntArray11(int[] intArray)
	{
		
		int length;
		length = intArray.length;
      	userVals = new int[length];
		int i = 0;
		for (i = 0; i < length; ++i) {
        	userVals[i] = intArray[i];
        }       
	}
	
	/* Make a string representation */
	/**
	 * Pretty Print  -- Empty String "[]"
	 *                  else "[e1, e2, ..., en]"
	 */
	@Override
	public String toString()
	{
		int length;
		length = userVals.length;
 		//userVals = new int[length];  // we should not redefine it again or it will be wrong
		String userString = new String();
		userString = "[";
		if(length ==0) {userString = userString.concat("]"); return userString;}
		for (int i = 0;i < (length - 1);i++)
		{
       		userString = userString.concat(userVals[i] + ", ");
		}
		userString = userString.concat(userVals[length-1] + "]");
		return userString;  // return string type 
		
	}

	/* Getters and Setters */

	/** get the number of elements stored in the array  
	 * @return number of elements in the array
	*/
	public int getNelem()
	{
		return userVals.length; 
	}

	/** get the Element at index  
	 * @param index of data to retrieve 
	 * @return element if index is valid else  return 
	 * 		Integer.MIN_VALUE
	*/
	public int getElement(int index)
	{
		int length;
		length = userVals.length;
		int element = 0;
		if(index >= 0 && index < length){
			element = userVals[index];
			return element;
		}
		else
			return Integer.MIN_VALUE;	
	}

	/** retrieve a copy of the stored Array
	 * @return a deep copy of the Array. A new int array should be
	 * 		constructed of the correct size and values should
	 * 		copied into it.  
	*/
	public int[] getArray()   
	{
 		int length;
		length = userVals.length;
      	int[] tempVals = new int[length];
      	for (int i = 0; i < length; i++) {
         	tempVals[i] = userVals[i];
        }
		for (int i = 0; i < length; i++) {
         	userVals[i] = tempVals[i];
        }
        return (int[]) userVals;
	}

	/** set the value of an element in the stored array
	 * @param index of element to store. Must be a valid index 
	 * @param element the data to insert in the array
	 * @return true if element set was successful
	*/
	public boolean setElement(int index, int element)
	{
		int length;
		length = userVals.length;
		if(index >= 0 && index < length){
		  userVals[index] = element;
		  return true;
		}
		return false;
	}

	/** Insert an element at index in the array
	 * @param index where to insert. Must be between 0 and number of
	 *              elements in the array
	 * @param element the data to insert in the array
	 * @return true if element insertion was successful
	*/
	public boolean insert(int index, int element)
	{
		int length;
		length = userVals.length;
		int[] tempVals = new int[length];	
		int i = 0;
		if(length == 0 && index == 0) {userVals = new int[1]; userVals[0] = element;return true;}
		else if(length != 0 && index >= 0 && index <= length){
			tempVals = this.getArray();
			length = length + 1;
      		userVals = new int[length]; // array size add 1
			for (i = 0; i < index; i++) {
	         	userVals[i] = tempVals[i];
	      	}
	      	userVals[index] = element; 
			for (i = index + 1; i < length; i++){
				userVals[i] = tempVals[i-1];
			}
			return true;
		}
		else return false;
	}

	/** Delete a element at index
	 * @param index of element to delete 
	 * @return true if element deletion was successful, false otherwise
	*/
	public boolean delete(int index)  
	{
		int length;
		length = userVals.length;
		int[] tempVals = new int[length];
		tempVals = this.getArray(); 		
  		if(index >=0 && index < length){
  			userVals = new int[length - 1]; // array size reduce by 1
	  		for (int i = 0;i < length - 1;i++){
	  			if(i >= index)
	  			 	userVals[i] = tempVals[i + 1];
	  			else userVals[i] = tempVals[i];
	  		}
	  	return true;
  		}
		return false;
	}

	/** reverse the order of the elements in the array 
	*/
	public void reverse()
	{ 
		int i = 0;
		int tempVal = 0;
		for (i = 0; i < (userVals.length / 2); ++i) {
         	tempVal = userVals[i];                        // Temp for swap
         	userVals[i] = userVals[userVals.length - 1 - i]; // First part of swap
         	userVals[userVals.length - 1 - i] = tempVal;     // Swap complete
      	}
	}

	/** reverse the order of the elements in the array from start to
 	*   end index 
	*   @param start beginning index of to start the reverse
	*   @param end	ending index to end the reverse
	*   @return true if start and end index are valid, false otherwise
	*
	*/
	public boolean reverse(int start, int end)
	{
		int tempVal = 0;
		int i = 0;
		if(start>=0 && end < userVals.length && start <= end){
			for (i = 0;i<(end - start + 1)/2;i++){
			tempVal = userVals[start + i];                        // Temp for swap
         	userVals[start + i] = userVals[end - i ]; // First part of swap
         	userVals[end - i] = tempVal;     // Swap complete
		 	}
		 	return true;
		}
		else 
			return false;
	}

	/** swap two elements in the array 
	*   @param index1 index of first element 
	*   @param index2 index of second element
	*   @return true if index1 and index2 are valid, false otherwise
	*
	*/
	public boolean swap(int index1, int index2)
	{
      	int tempVal = 0;  
      	if(index1 >= 0 && index1 < userVals.length && index2 >= 0 && index2 < userVals.length){
			tempVal = userVals[index1];                       
		    userVals[index1] = userVals[index2]; 
		    userVals[index2] = tempVal; 

		    return true;
		}
	    

		return false;
	}

}
// vim: ts=4:sw=4:tw=78:
