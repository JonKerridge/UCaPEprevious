package c21.net
 
import jcsp.lang.*






class Gatherer implements CSProcess{
  def ChannelInput fromNodes

  void run() {
    while (true) {
      def d = fromNodes.read()
      println "Gathered from ${d.toString()}"      
    }    
  }
}
