package exceptions;

public class WallHitException extends RuntimeException{
	
	//message that is printed if the exception.printMessage is called
		private String message;
		
		/**
		 * creates a new instance of the runtimeException
		 */
		public WallHitException (){
			message = "CANNOT READ WHEN MOUSE IS INBETWEEN CELLS";
		}
		
		/**
		 * creates a new instance of runtimeException
		 * 
		 * @param message the message that is printed when errors printMessage is called
		 */
		public WallHitException (String message){
			this.message = message;
		}
		
		/**
		 * Prints message on the serial
		 */
		public void printMessage () {
			System.out.println(message);
		}
		
		/**
		 * @return returns the message
		 */
		public String getMessage () {
			return message;
		}
	
}
