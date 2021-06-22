
# CM30174/50206

Template project for Rover coursework.

__<a name="bugs"></a>NOTE: You are encouraged to use the repository's issues tab to report bugs and requests features that are necessary.  Both would be addressed in a timely manner and would generally result in a new release. When a new release is available, a mail would be sent to everyone enrolled in the course detailing the changes and the new version string.__



## Getting Started

1. [Installing Eclipse](https://wiki.eclipse.org/Eclipse/Installation) 
2. [Installing Jason & Jason Eclipse plugin](http://jason.sourceforge.net/mini-tutorial/eclipse-plugin/)
3. Getting started with Jason (see Course reading list)
4. Starting the course work


## Contents
 - [The Environment](#env)
 - The Agent
   - [Agents actions within the environment](#awe)
     - [Scan](#scan)
     - [Move](#move)
     - [Collect](#collect)
     - [Deposit](#deposit)
   - [Extending the capability of Agents](#eac)
	   - [get_environment_version](#gev)
      -  [get_remaining_energy](#gre)
      -  [Important note & provisional internal actions](#pia)
 - [Updates](#updates)
 - Troubleshooting
   - [Ant is not properly configured](#ant)
   - [Error in Centralised MAS environment creation java.lang.ClassNotFoundException: rover.RoverWorld](#cnf)
 - [Bugs & Feature requests](#bugs)



## <a name="env"></a>The Environment
The environment for this coursework is provided as a jar file (`libs/rover_cw.jar`). 
Using this environment requires the following line to be present in your coursework's mas2j file(s);

__`environment:	rover.RoverWorld(<path_to_mas2j_file>,  <scenario_id>, <ui_option>, <scenario_file>)`__

where;

_**path_to_mas2j_file**_: Name of mas2j file or its path if located elsewhere.

_**scenario_id**_: An integer representing the id of the scenario to load.

_**ui_option**_: A Boolean flag which determines if a UI of the environment would be instantiated or not.

_**scenario_file**_: Path to JSON file containing scenario specifications.

example;
__`environment:	rover.RoverWorld("demo.mas2j",  0, yes, "scenarios.json")`__


_**NOTE**: If you choose to use this blank project, this has already been set up in the various mas2j files and would not require you to take additional action._

## <a name="ag"></a>Agents
Agents are loaded via the mas2j file and each agent is expected to be configured before connecting to the environment.
Configuring an agent incolves specifying its `max_capacity`. `scan_range`, `speed` and where necessary `preferred_resource_type`.

The values assigned for `max_capacity`, `scan_range` and `speed` must be positive integers that add up to a total of 9.

i.e. `speed` can not have a value below 0 or above 9 and where `speed` is 9, both `max_capacity` and `scan_range` must be 0.

The environment would confirm this to ensure every agent is appropriately configured and where a violation is detected, an adequate error message would be displayed and the affected agent(s) would be reconfigured as follows: 

`max_capacity`=3, `scan_range` = 3 and `speed` = 3.

Adding an Agent to the environment requires the following line to be present in your coursework's mas2j file(s);

__`agents: <agent_name> [max_capacity=<int_val>, scan_range=<int_val>, speed=<int_val>"] #<instances>;`__

where;

__*agent_name*__: Name of agent to add. This is the same name as the Agent's `.asl` file

__*instances*__:  A positive non-zero integer that specifies the instances of that agent to add to the environment. 

example;
__`dummy_bot [max_capacity=3, scan_range=3, speed=3] #1;`__

In this example, one instance of the agent `dummy_bot` defined in the file `dummy_bot.asl` would be added into the environment. It would have a max_capacity of 3, a scan range of 3 and its speed will be 3.

`preferred_resource_type` is a string and is declared as shown in the example below. This should be configured if you wish to manually assign agents to resource types in the multi-resource scenarios. The available options are `gold` and `diamond`. 

example;
__`dummy_bot [max_capacity=3, scan_range=3, speed=3, preferred_resource_type=gold] #1;`__

### <a name="awe"></a>Agents actions within the environment
Within the environment, your Agent(s) would be able to `scan` the map, `move` around, `collect` resources and `deposit` collected resources. Actions performed by Agents cost energy and when an Agent runs out of energy, it is unable to perform an action within the environment. After performing an action, an agent would get feedback indicating the success or failure of execution. This feedback would be in two forms. The first is an indication of the success or failure of the action while the second is a `percept` or `event` which conveys information about the task performed and its execution status code. 

Action Feedback have the format: 
__`result(<action_name_in_lower_case>, <status_code>)`__

where;

__*action_name_in_lower_case*__: Action performed by agent. E.g.: `scan`, `deposit`, ...

__*status_code*__: Integer code indicating the action execution success/error.


example;
__`result(scan, -1)`__ 

Which means that an agent scanned the environment successfully but did not find any resource.

#### <a name="scan"></a>Scan
Scanning allows Agents to identify items on the map. To scan, an Agent is expected to provide a range which is a positive non-zero integer that corresponds to the scan radius. The range affects the amount of energy consumed and specifying a range larger than the scan range of the agent would not cause it to scan further than it is allowed to.

*example;* __`scan(3)`__

##### Possible status code (after scanning):

|__Status Code__ | __Description__|
|-------------|--------|
| __-1__ | Scanning was successful and nothing was found |
| __0__ | Scanning was successful and at least one item was discovered |
| __1__ | The Agent does not have enough energy to scan at the given range|
| __2__ | The Agent is unable to scan/does not support scanning/ has a scan range of 0 |
| __3__ | The scan range parameter provided is not a valid number |

__Note__: Agents would still be charged energy when scanning returns nothing.

##### Limits

   - Scan operations can not locate an Agent's base.
   
   - Scan operations would not detect the agent scanning.
   
   - Scan operations would not detect resources carried by other Agents.
   
   - Scan operations would not detect resources deposited at the base.


#### <a name="move"></a>Move
An agent may `move` around the map by specifying `displacement` and `speed`. Displacement is the distance to move along the `x` and `y` axis and is an integer (positive or negative) while speed is the travel speed which determines how fast an Agent would reach its destination.

*syntax;* __`move(X, Y, S)`__

where;

__*X*__: X displacement (This is an integer value with range -infinity < X < infinity)

__*Y*__: Y displacement (This is an integer value with range -infinity <Y < infinity)

__*S*__: Travel speed (This is a non-zero integer)


*example;* __`move(0, 1, 1)`__

##### Possible status code (after moving):

|__Status Code__ | __Description__|
|-------------|--------|
| __0__ | Movement was successful and Agent is at its destination |
| __1__ | The Agent does not have enough energy to move to the location at the specified speed|
| __2__ | The Agent is unable to move/does not support moving/ has a speed of 0 |
| __3__ | The displacement or speed provided is not a valid number |
| __4__ | An Agent is currently located at the position being travelled to| 

##### Limits

   - No two Agents may be located at the same spot on the map except at the Base.
   
   - Agents would not move at a speed beyond their `speed` limit assigned during configuration.
   
   - An Agent requires a minimum `speed` of `1` to move and setting a `speed` of `0` means an Agent would be stationary.
   
   - The world wraps upon itself.
   
   
   
#### <a name="collect"></a>Collect
An Agent may `collect` a resource from any location on the map except the Base. To collect a resource, an Agent must be located over the resource,

*syntax;* __`do(collect)`__


##### Possible status code (after collecting):

|__Status Code__ | __Description__|
|-------------|--------|
| __0__ | Agent collected one resource from the Map successfully |
| __1__ | The Agent does not have enough energy to move to collect the resource|
| __2__ | The Agent is unable to collect/does not support resource collection/ has a max_capacity of 0 |
| __3__ | There is no resource at the Location |
| __4__ | The Agent is carrying its maximum load and has no space to accommodate the new item  |

##### Limits

   - A collect operation picks up only one resource at a time. i.e. To pick three items, you need to run the collect operation three times.
   
   - Agents can only pick one type of resource. This should not matter except in multi-resource scenarios.
   
   
#### <a name="deposit"></a>Deposit
An Agent may `deposit` a resource at any location on the map. Depositing a resource at a Base adds to the amount of resources collected and earn you points. An Agent must be located at the exact point where it wishes to deposit its payload.

*syntax;* __`do(deposit)`__

##### Possible status code (after deposit):

|__Status Code__ | __Description__|
|-------------|--------|
| __0__ | Agent deposited one resource to the Map successfully (To deposit at a Base, the Agent has to be at the base location |
| __1__ | The Agent does not have enough energy to deposit the resource|
| __2__ | The Agent is unable to deposit /does not support resource collecting and hence depositing / has a max_capacity of 0 |
| __3__ | The Agent does not have a resource to deposit |

##### Limits

   - A deposit operation drope only one resource at a time. i.e. To drop three items, you need to run the deposit operation three times.
   - An Agent drops resources on the tile it currently occupies.
   
   - To gain points, resources must be dropped at the Base. i.e. An Agent must be standing on t ( __NOTE:__ Dropping resources a block away from the Base does not count towards your score)
   
   
### <a name="eac"></a>Extending the capability of Agents

Apart from the actions listed above, an Agent may extend its capability via internal actions.
Some internal actions have been provided for you such as;

#### <a name="gev"></a>*get_environment_version*: 
Over the period of your coursework, modifications made to the Environment would result in the release of new versions. You may choose to use this to check that your Agent is interacting with the intended environment. The Environment's version is presented as a version string. 
i.e. 1.0.0 
- usage:  __*get_environment_version(X)*__
			This would store the version string of type String in the variable X. 
			


#### <a name="gre"></a>*get_remaining_energy*: 
- usage:  __*get_remaining_energy(X)*__
			This would store the Agent's current energy of type double in the variable X. 

__<a name="pia"></a>NOTE__: For this coursework, you are limited to create internal actions for navigation purposes only. A very basic implementation of this has been made available in the `libs/rovers_cw.jar` jar file under the package `rover.ia`. This implementation is made available to get you started and is not optimised (unsuitable for submission). These internal actions would be demonstrated during the lab sessions and are briefly discussed below;

- __*add_journey*__: Keeps track of movement made by the Agent.
- __*clear_map*__: Clears the map the Agent built.
- __*get_base_location*__: Retrieves the location of the base.

##  <a name="update"></a>Updates

As discussed above, revisions to the environment would result in the release of a new version. This means that a new `rover_cw.jar` file would be made available. When this occurs, navigate to the `release` tab of this repository to obtain the latest version. Every release would be accompanied with a version string, release timestamp and a Changelog detailing the issue(s) it addresses. Note: Issue here refers to issues logged on this repository.

To use an update, simply replace the `rover.cw_jar` file located in the `libs` directory of your project with the new `rover_cw.jar` file and run your application. To ensure your Agent is working with the intended environment version, consider using the internal action `get_environment_version` as discussed above.


## Troubleshooting

### <a name="ant"></a>Ant is not properly configured
To fix this error (Windows only)
- Ensure your `JAVA_HOME` is set and points to JDK's installation directory on your Computer.
- Ensure `JAVA_HOME` has been added to `path`. i.e. Add `%JAVA_HOME%;` to the end of `path` environment variable.
- Navigate to the directory `jason-2.2a`on your Computer and then click on `libs/jason-2.2.jar`. This should launch a dialog. Ensure the data shown is accurate and then click on `Save configuration and Exit`
- Launch (or Restart) Eclipse and run the project.

### <a name="cnf"></a> [CentralisedEnvironment] Error in Centralised MAS environment creation java.lang.ClassNotFoundException: rover.RoverWorld
To fix this error (Universal)
- Ensure you have added the `rover_cw.jar` file to your project's build path.


__NOTE:__ If you run into any issue that has not been documented here contact the course tutors ASAP.
