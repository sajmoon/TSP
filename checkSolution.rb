#!/usr/bin/ruby

puts "Checking Result"

list = Hash.new(false)
dups = 0
i = 0

STDIN.read.split("\n").each do |a|
  i = i + 1
  unless list.has_key?(a)
    list[a] = true
  else
    dups = dups + 1
  end
end

puts "Antal rader: #{i}"
puts "Dups: #{dups}"
