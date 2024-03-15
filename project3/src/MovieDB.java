/*****************************************************************************************
 * @file  MovieDB.java
 *
 * @author   John Miller
 */

import java.util.ArrayList;

import static java.lang.System.out;

/*****************************************************************************************
 * The MovieDB class makes a Movie Database.  It serves as a template for making other
 * databases.  See "Database Systems: The Complete Book", second edition, page 26 for more
 * information on the Movie Database schema.
 */
class MovieDB
{
    /*************************************************************************************
     * Main method for creating, populating and querying a Movie Database.
     * @param args  the command-line arguments
     */
    public static void main (String [] args)
    {
        out.println ();

        var movie = new Table ("movie", "title year length genre studioName producerNo",
                                        "String Integer Integer String String Integer", "title year");

        var cinema = new Table ("cinema", "title year length genre studioName producerNo",
                                          "String Integer Integer String String Integer", "title year");

        var movieStar = new Table ("movieStar", "name address gender birthdate",
                                                "String String Character String", "name");

        var starsIn = new Table ("starsIn", "movieTitle movieYear starName",
                                            "String Integer String", "movieTitle movieYear starName");

        var movieExec = new Table ("movieExec", "certNo name address fee",
                                                "Integer String String Float", "certNo");

        var studio = new Table ("studio", "name address presNo",
                                          "String String Integer", "name");

        var film0 = new Comparable [] { "Star_Wars", 1977, 124, "sciFi", "Fox", 12345 };
        var film1 = new Comparable [] { "Star_Wars_2", 1980, 124, "sciFi", "Fox", 12345 };
        var film2 = new Comparable [] { "Rocky", 1985, 200, "action", "Universal", 12125 };
        var film3 = new Comparable [] { "Rambo", 1978, 100, "action", "Universal", 32355 };

        out.println ();
        movie.insert (film0);
        movie.insert (film1);
        movie.insert (film2);
        movie.insert (film3);
        movie.print ();
        movie.printIndex();

        var film4 = new Comparable [] { "Galaxy_Quest", 1999, 104, "comedy", "DreamWorks", 67890 };
        out.println ();
        cinema.insert (film2);
        cinema.insert (film3);
        cinema.insert (film4);
        cinema.print ();
        cinema.printIndex();
        movie.printIndex();
        Table uniont = movie.union(cinema);
        uniont.print();


        var star0 = new Comparable [] { "Carrie_Fisher", "Hollywood", 'F', "9/9/99" };
        var star1 = new Comparable [] { "Mark_Hamill", "Brentwood", 'M', "8/8/88" };
        var star2 = new Comparable [] { "Harrison_Ford", "Beverly_Hills", 'M', "7/7/77" };
        out.println ();
        movieStar.insert (star0);
        movieStar.insert (star1);
        movieStar.insert (star2);
        movieStar.print ();

        var cast0 = new Comparable [] { "Star_Wars", 1977, "Carrie_Fisher" };
        out.println ();
        starsIn.insert (cast0);
        starsIn.print ();

        var exec0 = new Comparable [] { 9999, "S_Spielberg", "Hollywood", 10000.00f };
        out.println ();
        movieExec.insert (exec0);
        movieExec.print ();

        var studio0 = new Comparable [] { "Fox", "Los_Angeles", 7777 };
        var studio1 = new Comparable [] { "Universal", "Universal_City", 8888 };
        var studio2 = new Comparable [] { "DreamWorks", "Universal_City", 9999 };
        out.println ();
        studio.insert (studio0);
        studio.insert (studio1);
        studio.insert (studio2);
        studio.print ();

        movie.save ();
        cinema.save ();
        movieStar.save ();
        starsIn.save ();
        movieExec.save ();
        studio.save ();

        movieStar.printIndex ();
        //--------------------- project: title year

        out.println ();
        var t_project = movie.project ("title year");
        t_project.print ();
        //++++++++++++++tests written by vishal++++++++++++++++++++++++++++++//

        var t_project1 = movie.project ("title length studioName producerNo");
        t_project1.print ();

        //++++++++++++++tests written by vishal++++++++++++++++++++++++++++++//
        //--------------------- select: equals, &&

        out.println ();
        var t_select = movie.select (t -> t[movie.col("title")].equals ("Star_Wars") &&
                                            t[movie.col("year")].equals (1977));
        t_select.print ();

        //--------------------- select: <

        out.println ();
        var t_select2 = movie.select (t -> (Integer) t[movie.col("year")] < 1980);
        t_select2.print ();
        //++++++++++++++tests written by vishal++++++++++++++++++++++++++++++//

        out.println ();
        var t_select3 = movie.select ("year<=1990");
        t_select3.print ();

        //++++++++++++++tests written by vishal++++++++++++++++++++++++++++++//
        //--------------------- indexed select: key

        out.println ();
        var t_iselect = movieStar.select (new KeyType ("Harrison_Ford"));
        t_iselect.print ();

        //--------------------- union: movie UNION cinema

        out.println ();
        var t_union = movie.union (cinema);
        t_union.print ();

        //--------------------- minus: movie MINUS cinema

        out.println ();
        var t_minus = movie.minus (cinema);
        t_minus.print ();

        //--------------------- equi-join: movie JOIN studio ON studioName = name

        out.println ();
        var t_join = movie.join ("studioName", "name", studio);
        t_join.print ();

        //--------------------- natural join: movie JOIN studio

        out.println ();
        var t_join2 = movie.join (cinema);
        t_join2.print ();

        //---------------------- theta join: movie Join studio
        out.println ();
        var t_join3 =movie.join ("studioName == name", studio);
        t_join3.print();


        //---------------------- i join : movie
        out.println ();
        var t_join4=movie.i_join ("title year", "title year", cinema);
        t_join4.print();
        //is default primary key that was created during the creation of table
        // same as attributes1 and attributes2








        ArrayList<String> selecttimes1=new ArrayList<>();
        ArrayList<String> selecttimes2=new ArrayList<>();
        ArrayList<String> selectnomaptimes1=new ArrayList<>();
        ArrayList<String> selectnomaptimes2=new ArrayList<>();
        ArrayList<String> joinstimes1=new ArrayList<>();
        ArrayList<String> joinstimes2=new ArrayList<>();

        ArrayList<String> nomapjoinstimes=new ArrayList<>();
//        out.println ();
        for(int RUNS=1;RUNS<=15;RUNS++){
            var test = new TupleGeneratorImpl ();
            test.addRelSchema ("Student",
                    "id name address status",
                    "Integer String String String",
                    "id",
                    null);

            test.addRelSchema ("Professor",
                    "id name deptId",
                    "Integer String String",
                    "id",
                    null);

            test.addRelSchema ("Course",
                    "crsCode deptId crsName descr",
                    "String String String String",
                    "crsCode",
                    null);

            test.addRelSchema ("Teaching",
                    "crsCode semester profId",
                    "String String Integer",
                    "crcCode semester",
                    new String [][] {{ "profId", "Professor", "id" },
                            { "crsCode", "Course", "crsCode" }});

            test.addRelSchema("Transcript",
                    "studId crsCode semester grade",
                    "Integer String String String",
                    "studId",//crsCode semester removed them since to join indexes must be same
                    new String [][] {{ "studId", "Student", "id"},
                            { "crsCode", "Course", "crsCode" },
                            { "crsCode semester", "Teaching", "crsCode semester" }});
            var tables = new String [] { "Student", "Professor", "Course", "Teaching", "Transcript"};
            var size=10000*RUNS;
            var tups   = new int [] { size,size,size,size,size };//inserting 10000 random values
            var resultTest = test.generate (tups);

            var student = new Table ("Student",
                    "id name address status",
                    "Integer String String String",
                    "id");
            var Professor = new Table ("Professor",
                    "id name deptId",
                    "Integer String String",
                    "id");
            var Course= new Table("Course",
                    "crsCode deptId crsName descr",
                    "String String String String",
                    "crsCode");
            var Teaching=new Table("Teaching",
                    "crsCode semester profId",
                    "String String Integer",
                    "crsCode semester") ;
            var Transcript=new Table("Transcript",
                    "studId crsCode semester grade",
                    "Integer String String String",
                    "studId");//crsCode semester removed them since to join indexes must be same

            Comparable key1=new String();
            Comparable key2=new String();
            for (var i = 0; i < resultTest.length; i++) {
                for (var j = 0; j < resultTest [i].length; j++) {
                    var tempfilm=new Comparable[resultTest [i][j].length];
                    int count=0;
                    for (var k = 0; k < resultTest [i][j].length; k++) {
//                    out.print (resultTest [i][j][k] + ","); //printing the generated random values
                        tempfilm[count++]=resultTest [i][j][k]; //adding the generated random values
                    } // for
                    if(i==0){
                        student.insert(tempfilm);
                        if(j==resultTest [i].length-1){
                            key1=resultTest[0][0][0];
                            out.println("++++++++++++++++++++++++++"+key1+"RUNS===="+RUNS);
                        }
                    }
                    if(i==1){
                        Professor.insert(tempfilm);
                        if(j==resultTest [i].length-1){
                            key2=resultTest[1][0][0];
                            out.println("++++++++++++++++++++++++++"+key1+"RUNS===="+RUNS);
                        }
                    }
                    if(i==2){
                        Course.insert(tempfilm);
                    }
                    if(i==3){
                        Teaching.insert(tempfilm);
                    }
                } // for
                out.println ();
            } // for

            var quantity_of_select_map=100000000;
            int num_of_runs_map=1;
            var quantity_of_select_nomap=1000;
            int num_of_runs_nomap=1;
            var quantity_of_joins=500;
            int num_of_joinruns=1;
////----------------select map testing by vishal change map to TREE_MAP, HASH_MAP, LINHASH_MAP, BPTREE_MAP and run different codes -------------------//
//            runselect( student,quantity_of_select_map,num_of_runs_map,selecttimes1,key1);
//            runselect( Professor,quantity_of_select_map,num_of_runs_map,selecttimes2,key2);

            //----------------select nomap testing by vishal -------------------//
//            runnomapselect( quantity_of_select_nomap,student,num_of_runs_nomap,selectnomaptimes1,key1);
//            runnomapselect( quantity_of_select_nomap,Professor,num_of_runs_nomap,selectnomaptimes2,key2);

            //----------------i_join testing by vishal -------------------//
            runjoin( quantity_of_joins,student,Professor,"id","id",num_of_joinruns,joinstimes1);
            runjoin( quantity_of_joins,student,Transcript,"id","studId",num_of_joinruns,joinstimes2);

            //----------------no_map join testing by vishal -------------------//


//            runnomapjoin( quantity_of_joins*i,movie1,cinema1,"title",5,nomapjoinstimes);


        }

        out.println();
        out.println("time taken for mapped select is nano seconds "+selecttimes1);
        out.println();
        out.println();
        out.println("time taken for mapped select is nano seconds "+selecttimes2);
        out.println();
        out.println();
        out.println("time taken for no map select is nano seconds "+selectnomaptimes1);
        out.println();
        out.println();
        out.println("time taken for no map select is nano seconds "+selectnomaptimes2);
        out.println();

        out.println();
        out.println("time taken for mapped join is milli seconds "+joinstimes1);
        out.println();
        out.println();
        out.println("time taken for mapped join is milli seconds "+joinstimes2);
        out.println();
//        out.println();
//        out.println(nomapjoinstimes);
//        out.println();

    } // main

    public static  void runselect(Table anytable,int quantity_of_select,int num_of_runs,ArrayList<String> select,Comparable key){
        double time=0;
        var temprun = anytable.select (new KeyType (key));
        for(int i=0;i<num_of_runs;i++){ //take average of 5 selects
            long nano_startTime = System.nanoTime();
            for(int j=0;j<quantity_of_select;j++){ //run select a lot of times
                temprun = anytable.select (new KeyType (key));

            }
            long nano_endTime = System.nanoTime();

            time += (nano_endTime - nano_startTime) / quantity_of_select;

        }



        out.println("time================"+time);

            select.add(String.format("%.5f", time/num_of_runs));
            out.println("Average Time taken in nano seconds for indexed select: "+String.format("%.5f", time/num_of_runs));


    }
    public static  void runnomapselect(int quantity_of_select,Table anytable,int num_of_runs,ArrayList<String> nomapselect,Comparable key){
        double time=0;
        var temprun=anytable.select (STR."id == \{key}");
        for(int j=0;j<num_of_runs;j++){
            long nano_startTime = System.nanoTime();
            for(var i=0;i<quantity_of_select;i++){
                 temprun=anytable.select (STR."id == \{key}");
            }
            long nano_endTime = System.nanoTime();
            time+=(nano_endTime - nano_startTime)/quantity_of_select;//ignore the first iteration due to jit as told in pdf

        }
        nomapselect.add(String.format("%.5f", time/num_of_runs));
        out.println("Average Time taken in seconds for nomap select: "+String.format("%.5f", time/num_of_runs));//average of five iterations
    }


    public static  void runjoin(int quantity_of_joins,Table table1,Table table2,String JoinKey1,String JoinKey2,int num_of_runs,ArrayList<String> joins){
        double time=0;

        for(int j=0;j<num_of_runs+1;j++){
            long nano_startTime = System.nanoTime();
            for(var i=0;i<quantity_of_joins;i++){
                var temprun = table1.i_join (JoinKey1,JoinKey2, table2);//command we run

            }

            long nano_endTime = System.nanoTime();
            if(j>0){
                time+=((nano_endTime - nano_startTime)/quantity_of_joins)/1000000d;//ignore the first iteration due to jit as told in pdf
            }
        }
        joins.add(String.format("%.5f", time/num_of_runs));
        out.println("Average Time taken in milli seconds for indexed join: "+String.format("%.5f", time/num_of_runs));//average of five iterations
    }

    public static  void runnomapjoin(int quantity_of_joins,Table movie1,Table cinema1,String JoinKey,int num_of_runs,ArrayList<String> joins){
        double time=0;

        for(int j=0;j<num_of_runs+1;j++){
            long nano_startTime = System.nanoTime();
            for(var i=0;i<quantity_of_joins;i++){
                var temprun = movie1.join (JoinKey, JoinKey, cinema1);
            }

            long nano_endTime = System.nanoTime();
            if(j>0){
                time+=(nano_endTime - nano_startTime)/1000000d;//ignore the first iteration due to jit as told in pdf
            }
        }
        joins.add(String.format("%.5f", time/num_of_runs));
        out.println("Average Time taken in MS seconds for No_MAP join: "+String.format("%.5f", time/num_of_runs));//average of five iterations
    }
} // MovieDB class

