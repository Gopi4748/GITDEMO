import Files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(payload.CoursePrice());

	//TC1 : Print no of courses
		
	int count =	js.getInt("courses.size()");
	System.out.println(count);
	
	//TC2 : Print purchase amount
	
   int purchaseamount=	js.getInt("dashboard.purchaseAmount");

   System.out.println(purchaseamount);
   
   //TC3 : Print title of first course
   
   String firstcourse=  js.getString("courses[0].title");
   System.out.println(firstcourse);
	
   // TC 4 : Print All course titles and their respective Prices
   
   for (int i=0;i<count;i++)
   {
		String coursetiles= js.get("courses["+i+"].title");
		System.out.println(coursetiles);
		System.out.println(js.get("courses["+i+"].price").toString());
	}

   //TC 5 : . Print no of copies sold by RPA Course
   System.out.println("Print no of copies sold by RPA course");
   for (int i=0;i<count;i++)
   {
		String coursetiles= js.get("courses["+i+"].title");
		if(coursetiles.equalsIgnoreCase("RPA"))
		{
			int copies = js.get("courses["+i+"].copies");
			System.out.println(copies);
			break;
			
		}
	}
   
}

}