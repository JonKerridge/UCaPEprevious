package c16.net
 // copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import jcsp.lang.*
import groovyJCSP.*
import jcsp.net.*
import jcsp.net.tcpip.*
import phw.util.*

class PrintSpooler implements CSProcess {  
	
  def ChannelInput printerRequest
  def ChannelInput printerRelease
  def int spoolers = 2  
  
  void run() {
	def timer = new CSTimer()
    def spooling = 0
    def spoolChannels = []
    def spoolChannelLocations = [:]
    def unusedSpoolers = []
    def preCon = new boolean[spoolers + 2 ]
    def printMap = [:]
    def jobMap = [:]
    preCon[0] = true
    0.upto(spoolers - 1) { i -> def c = NetChannel.net2one()
                                spoolChannels << c
                                spoolChannelLocations.put(i, c.getLocation() )
                                unusedSpoolers << i
                                preCon[i+2] = false
                         }
    def altChans = [ printerRelease, printerRequest ]
    altChans = altChans + spoolChannels
    def psAlt = new ALT ( altChans )
    while (true) {
      preCon[1] = (spooling < spoolers)
      def index = psAlt.select(preCon)
      switch (index) { 
        case 0:
          //user releasing a print channel
          def usedKey = printerRelease.read()
          unusedSpoolers.add(usedKey)
          preCon[usedKey + 2] = false
          spooling = spooling - 1
          // now print the spooled lines
          def lines = printMap.get(usedKey)
          print "\n\nOutputFor User ${jobMap.get(usedKey)}\n"
          println "Produced using spooler $usedKey \n\n"
          lines.each{ println "${it}" }
          println "\n\n================================\n\n"
          printMap.remove(usedKey)
          jobMap.remove(usedKey)
          break
        case 1:
          // user requested a print channel
          def job = printerRequest.read()
          def useChannelLocation = job.useLocation
          def userId = job.userId
          def useChannel = NetChannel.one2net(useChannelLocation)
          spooling = spooling + 1
          def useKey = unusedSpoolers.pop()
          preCon[useKey+2] = true
          printMap[useKey] = [] // initialise the printlist for this user
          jobMap[useKey] = userId
          useChannel.write(spoolChannelLocations.get(useKey) )
          useChannel.write( useKey )
          break
        default :
          // printline being received from a user
          def pLine = spoolChannels[ index - 2].read()
          printMap[pLine.printKey] << pLine.line
          timer.sleep(5000)
      } //switch      
    } //while
  } // run
} // class

      
    
    
    
    