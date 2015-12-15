the project comes in two flavours for running, building and using the performance tracking module.
you can choose to leverage maven or the eclipse fashion according to your perference


how to run the demo 
 prerequisite: jdk 1.5 or above, maven2.0 (jdk1.5.0_12 is incompatible to run the demo)

 maven apporach:

 	step 1: install depended jar file
 	mvn install:install-file -DgroupId=net.sf.ehcache -DartifactId=ehcache -Dversion=0.9 -Dpackaging=jar -Dfile={the ehcache-0.9.jar lib location}
 (note: most depended jar can be downloaded automatically by maven from its reporsitory, but there are some specific jar versions you can't find on it. in this case ehcache-0.9.jar, you have to install it by yourself, and you can find the jar file under lib folder that you just downloaded from svn repository.)

	 step 2: go to the project folder, performanceTracking, where the pom.xml is located. execute: mvn test
 
 	 step 3: that's it.

 eclipse apporach:

	 step 1: import the project

	 step 2: Add all the dependent jars from lib folder
 
 	 step 3: go to test file to trigger the unit test. 


----------------------------------------------------------------------------------------------------------------
put it together (how to use it in your own project)

  maven apporach: 

  	step 1: execute: mvn package
  you will get a jar file called lexmarkTransaction.jar under target folder that maven created for you.

 	step 2: put the jar under your project lib folder along with all the dependent jars that come with the performance tracking project

	step 3 (optional): copy the 'hibernate.cfg.xml' and 'tracker.properties' files into your project folder where your other configuration files go, after build, all those configuration files should go to WEB-INF\classes
(by skipping this step, you will use the default setting that come with the lexmarkTransaction.jar ,we'll disscuss this later in basic configuration section)

  	setp 4: use it. below is a sample code snippet demonstrating how you should use it:

	//@param targetSystem, webServiceName, accountID, loginUser		
	
	PerformanceTracker.startTracking("Siebel","requestInstallBaseLocation",
	"869D9","Alex");		
        	logger.debug("the sleep time is " + sleepTime + " milliseconds");			
				Thread.sleep(sleepTime);
 // <------------- your web service call goes here, replace it		
	PerformanceTracker.endTracking(); 

  eclipse apporach:
        step 1: add the performance tracking project as required project on your own project path.
    
	step 2 , step 3 and step 4 : same as 'maven apporach'

--------------------------------------------------------------------------------------------------------------
basic configuration

how configuration files work (jump to the meatier tips if you have no interest in how things work) :
 	the lexmarkTransaction.jar include all the related classes along with hibernate mapping files and configuration files.   the application will first to look configuration file in your own project, and if you don't have it, then it will look into the lexmarktransaction.jar for the configuration.  you can copy the hibernate configuration file in to your project resources folder or to where all your other configuration files go. by doing this, you don't have to build the transactiontracking project to get the jar file. and just make some real time changes to the config file you copied.


how to turn it on and off:
  	go to resources folder or your own configuration folder, look for file named tracker.properties. change the "status" value to "off" to trun it off. change it back to "on" to turn it on.


schemar recreate options:
 	in the hibernate.cfg.xml (also inside resources folder or your own configuration folder ), look for lines:

	<!-- Create the tables -->
        <property name="hibernate.hbm2ddl.auto">create</property>
change the value to "validate" to prevent the application from rebuilding db schemar. but make sure 
the value is "create" first time you use it.



--------------------------------------------------------------------------------------------------------------

advanced configuration

change the database folder structure for your hpersql database:
	in the hibernate.cfg.xml, look for line:
  <property name="connection.url">jdbc:hsqldb:data/hsql/performanceTracking</property>
  the data/hsql/performanceTracking reflects the database folder structure in your own project.
  you don't want to create spreate database for each of your project .you can name it to the database your portal 
  application uses. or you can add a slash(/) before the database naming path.(e.g.  /data/hsql/performanceTracking)
  to get all the projects using the same database schema under your root device directory (e.g. E:/), or for example, change it to /home/alex/lexmarkdb/performanceTracking, if you are working with Linux os.
 


  enjoy!

  
  
 



