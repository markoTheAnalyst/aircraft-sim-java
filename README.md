# Air Traffic Control System

## Overview
The Air Traffic Control System is a software system designed to monitor and manage air traffic within a country's airspace. It includes components for airspace observation, traffic alerting, simulation, and more.

## Features
- **Airspace Observation**: Constant monitoring of the airspace using radar systems.
- **Traffic Alerting**: Immediate alerts for any potential threats or collisions in the airspace.
- **Simulation**: A simulation environment for testing the system and various scenarios.
- **Data Logging**: Recording and logging of airspace activities and events.
- **User Interface**: GUI application for real-time visualization of airspace data and events.

## Components
- **Radar System**: Constantly monitors airspace and detects aircraft.
- **Main Application**: GUI application for real-time visualization and control.
- **Simulator**: Generates simulated air traffic for testing purposes.
- **Data Management**: Manages data storage, logging, and backups.

## Usage
1. **Setup**: Install necessary dependencies and configure system parameters.
2. **Run**: Start the main application and radar system.
3. **Monitor**: Use the GUI to monitor airspace activity and manage traffic.
4. **Simulate**: Use the simulator to test different scenarios and system responses.

## Configuration
- **config.properties**: Configure system parameters such as airspace dimensions, aircraft generation intervals, etc.
- **radar.properties**: Configure radar system parameters including update intervals, detection thresholds, etc.

## File Structure
- **/src**: Contains source code for the Air Traffic Control System.
- **/data**: Stores configuration files and logs.
- **/events**: Stores event logs and alerts.
- **/backup**: Stores periodic backups of system data.

## Dependencies
- JavaFX 12.0.2
