package c19.net.serviceB

import jcsp.lang.*
import jcsp.net2.*;
import jcsp.net2.tcpip.*
import groovyJCSP.*
import java.awt.*
import jcsp.awt.*

class Bprocess implements CSProcess, Serializable{
	
	void run(){
		def root = new ActiveClosingFrame ("SERVICE B")
		def main = root.getActiveFrame()
		def sorrylabel = new Label("This service is not yet available")
		main.setLayout(new BorderLayout())
		main.setSize(480, 640)
		main.add(sorrylabel)
		main.pack()
		main.setVisible(true)
		new PAR([root]).run()

	}
}
