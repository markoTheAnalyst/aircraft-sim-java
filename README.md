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
- **Radar System**: Constantly monitors airspace and detects aircrafts.
- **Main Application**: GUI application for real-time visualization and control.
- **Simulator**: Generates aircrafts and randomly deploys them on the map.
- **Data Management**: Manages data storage, logging, and backups.

## Configuration
- **config.properties**: Configure system parameters such as aircraft generation intervals, etc.
- **radar.properties**: Configure radar system parameters such as airspace dimensions, detection thresholds, etc.

## File Structure
- **/src**: Contains source code for the Air Traffic Control System.
- **/alert**: Stores serialized objects representing alerts generated by the Air Traffic Control System.
- **/events**: Stores textual files containing information about events detected by the Air Traffic Control System. Each event file is named according to the timestamp when the event occurred. These files contain the timestamp, the position and the direction (north, east, etc.) of a foreign military aircraft.

## Dependencies
- JavaFX 12.0.2
