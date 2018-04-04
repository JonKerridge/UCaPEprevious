package c21.net
  
    
import jcsp.lang.*
import jcsp.net2.*
import jcsp.net2.tcpip.*
import groovyJCSP.*
import jcsp.userIO.*

def nodeIP = "127.0.0.1"
def nodeAddress = new TCPIPNodeAddress(nodeIP, 3000)
Node.getInstance().init(nodeAddress)
def fromNodesToGen = NetChannel.net2one()	//cn 50

println "Data Generator Starting"
def processList = new DataGenerator ( fromNodes: fromNodesToGen, interval: 500 )

new PAR ([ processList]).run()
