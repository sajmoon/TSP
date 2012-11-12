local_filename = "/indata.txt"

doc = ""
r = Random.new
100.times do 
  doc += r.rand(1...100).to_s + "." + r.rand(1...100).to_s+ " "
  doc += r.rand(1...100).to_s + "." + r.rand(1...100).to_s+"\n"
end

puts doc
