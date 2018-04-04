package c19.net.groupLocationService

import jcsp.lang.*
import jcsp.net2.*
import java.awt.*
import jcsp.awt.*
import groovyJCSP.PAR


class GLinterface implements CSProcess, Serializable {
	
	def ChannelOutput nameChannel
	def ChannelOutput locationChannel
	def ChannelInput label1Config
	def ChannelInput label2Config
	
	void run(){
		def root = new ActiveClosingFrame ("Group Location Finder")
		def main = root.getActiveFrame()
		def requestlabel = new Label("Group Name ?")
		def enterNameField = new ActiveTextEnterField (null, nameChannel)
		def enterName = enterNameField.getActiveTextField ()
		def locationField = new ActiveTextEnterField (null, locationChannel)
		def locationName = locationField.getActiveTextField ()
		def label1 = new ActiveLabel (label1Config, "")
		def label2 = new ActiveLabel (label2Config, "")
		def container = new Container()
		container.setLayout(new GridLayout(5,1))
		container.add(requestlabel)
		container.add(enterName)
		container.add(label1)
		container.add(label2)
		container.add(locationName)
		main.setLayout(new BorderLayout())
		main.setSize(480, 640)
		main.add(container)
		main.pack()
		main.setVisible(true)
		new PAR([root, enterNameField, label1, label2, locationField]).run()
	}
}
