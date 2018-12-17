# Pricer Test

1. Open a terminal window/command prompt
2. Clone this project.
3. `cd PricerTest` (Or whatever folder you cloned it into)
4. `mvn clean verify`

All dependencies should now be downloaded and the example PricerTest will have run successfully in headless mode (Assuming you have Firefox installed in the default location)

5. To run all test with Chrome browser run `mvn clean verify -Dbrowser=chrome -Dheadless=false`

### What should I know?

Yes you can specify other browsers to use by using one of the following switches:

- -Dbrowser=firefox
- -Dbrowser=ie
- -Dbrowser=edge
- -Dbrowser=opera

If you want to toggle the use of chrome or firefox in headless mode set the headless flag (by default the headless flag is set to true)

- -Dheadless=true
- -Dheadless=false

You don't need to worry about downloading the IEDriverServer, EdgeDriver, ChromeDriver , OperaChromiumDriver, or GeckoDriver binaries, this project will do that for you automatically.

If the tests fail screenshots will be saved in \${project.basedir}/target/screenshots
