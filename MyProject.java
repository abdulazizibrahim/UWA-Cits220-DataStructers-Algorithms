/**
   
     * Units:-CITS2200 Data Structures and Algorithms
     * Assessment:- Project 2020
     * @author abdul aziz ibrahim
     */
package pkg;
public class MyProject implements Project {
	/**
	 * Total number of changes for 
	 * floodFillCount method.
	 */
		int changes = 0;	
		/**
	     * To store the maximum brightness of 
	     * each path in darkestPath method.
	     */
		int max  =0 ;
		/**
	     * To store the brightness of the darkest path 
	     * from all possible paths in darkestPath method.
	     */
		int smax = 256;
		/**
	     * To reset variables for method specific static use
	     * in floodFillCount and darkestPath method
	     */
		int iterator = 0;
		/**
	     * The method returns the number of pixels changed when 
	     * performing black flood-fill on the selected pixel.
	     * @param image The matrix containing pixels.
	     * @param row The row coordinate of the selected pixel
	     * @param col the column coordinate of the selected pixel.
	     * @return changes The number of pixels changed
	     * Time Complexity O(P)
	     */
	public int floodFillCount(int[][] image, int row, int col) {
		if(row > image.length -1 || row < 0 || col > image[0].length || col < 0)
		{
			return 0;
		}
		final int num = image[row][col];
		if(iterator == 0)
		{
			changes = 0;
		}
		if(num == 0)
		{
			return 0;
		}
		if(image[row][col] == num)
		{
			image[row][col] = 0;
			changes ++;
		}
		if(col > 0 && image[row][col-1] == num)
		{
			iterator ++;
			floodFillCount(image, row, col - 1);
			iterator--;
		}
		if(col < image[0].length - 1 && image[row][col+1] == num)
		{
			iterator ++;
			floodFillCount(image, row, col + 1);
			iterator--;
		}
		if(row > 0 && image[row-1][col] ==  num)
		{
			iterator++;
			floodFillCount(image, row - 1, col);
			iterator--;
		}
		if(row < image.length - 1 && image[row+1][col] == num)
		{
			iterator++;
			floodFillCount(image, row + 1, col);
			iterator--;
		}
		
		return changes;
	}
	/**
     * The method computes the brightest k*k square 
     * possible from the image and returns that value.
     * @param image The matrix containing pixels.
     * @param k the value for the size of the square.
     * @return maxsum the sum of pixel of the brightest square.
     * Time Complexity O(P)
     */
	public int brightestSquare(int[][] image, int k) {
		if(k > image.length || k > image[0].length)
		{
			return 0;
		}
		int [][] array = new int[image.length][image[0].length];
		for(int j =0; j<image[0].length; j++)
		{
			int sum = 0;
		    for (int i=0; i<k; i++)
		    {
		    	sum += image[i][j];
		        array[0][j] = sum;
		    }	
		    for (int i=1; i<image.length-k+1; i++){
		        sum += (image[i+k-1][j] - image[i-1][j]);
		        array[i][j] = sum;
		      }
		}
		int maxsum = Integer.MIN_VALUE;
		for (int i=0; i<image.length-k+1; i++){
		      int sum = 0;
		      for (int j = 0; j<k; j++)
		         sum += array[i][j];
		      if (sum > maxsum){
		         maxsum = sum;
		      }
		     for (int j=1; j<image[0].length-k+1; j++){
		          sum += (array[i][j+k-1] - array[i][j-1]);
		          if (sum > maxsum){
		             maxsum = sum;
		          }
		     }
		 }
		return maxsum;
	}
	/**
     * From two given coordinates this method finds the darkest path
     * from all the possible path and return its brightness.
     * @param image The matrix containing pixels
     * @param ur The row coordinate of the starting pixel.
     * @param uc The column coordinate of the starting pixel
     * @param vr The row coordinate of the destination pixel.
     * @param vc The column coordinate of the destination pixel
     * @return smax The brightness of the darkest path
     * Time Complexity O(PlogP)
     */
	public int darkestPath(int[][] image, int ur, int uc, int vr, int vc) {
		if(ur < 0 || uc < 0 || vr < 0 || vc < 0 || ur > image.length || uc >image[0].length || vr > image.length || vc > image[0].length)
		{
			return 0;
		}
		final int row = ur;
		final int col = uc;
		if(iterator == 0)
		{
			max = 0;
			smax = 256;
		}
		if(uc == vc && ur == vr)
		{
			if(max < smax)
			{
				smax = max;
				max = 0;
			}
			ur = row;
			uc = col;
			return smax;
			
		}
		if(vc < uc && uc > 0)
		{
			if(max < image[ur][uc-1])
			{
				max = image[ur][uc-1];
			}
			iterator++;
			darkestPath(image, ur, uc-1, vr, vc);
			iterator--;
		}
		if(vc > uc && uc < image[0].length - 1)
		{
			if(max < image[ur][uc+1])
			{
				max = image[ur][uc+1];
			}
			iterator++;
			darkestPath(image, ur, uc+1, vr, vc);
			iterator--;
		}
		if(ur > vr && ur > 0)
		{
			if(max < image[ur-1][uc])
			{
				max = image[ur-1][uc];
			}
			iterator++;
			darkestPath(image, ur-1, uc, vr, vc);
			iterator--;
		}
		if(ur < image.length-1 && vr > ur)
		{
			if(max < image[ur+1][uc])
			{
				max = image[ur+1][uc];
			}
			iterator++;
			darkestPath(image, ur+1, uc, vr, vc);
			iterator--;
		}
		return smax;
	}
	/**
     * This method takes queries which contain row segments and returns an
     * array containing brightest pixel of each row segment.
     * @param image The matrix containing pixels
     * @param queries A 2 dimensional array containing information
     * regarding row segments.
     * @return answer An array containing maximum brightness of each row segment.
     * Time Complexity O(QlogC)
     */
	public int[] brightestPixelsInRowSegments(int[][] image, int[][] queries) {
		int i = 0;
		int [] answer = new int[queries.length];
		while(i<queries.length)
		{
			int row = queries[i][0];
			int start = queries[i][1];
			int end = queries[i][2];
			int max = -1;
			if(row < 0 || row > image.length-1 || start > end || start < 0 || start > image[0].length-1 || end > image[0].length)
			{
				return null;
			}
			for(int j=start; j<end;j++)
			{
				if(max < image[row][j])
				{
					max = image[row][j];
				}
				if(j != end -1 && j + 1 != end - 1)
				{
					if(max < image[row][j+1])
					{
						max = image[row][j+1];
						j++;
					}
					if(max < image[row][end-1])
					{
						max = image[row][end-1];
						end --;
					}
				}
			}
			answer[i] = max;
			i++;
		}
		
		return answer;
	}
	

}
