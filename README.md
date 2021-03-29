# DiscountsForBooks

Calculates Discounts for a list of books. Books are presented as command line args to the program in the form:

"Title,Year,Price"

i.e:

"Moby Dick,1851,£15.20"

"The Terrible Privacy of Maxwell Sim,2010,£13.14"

"Still Life With Woodpecker,1980,£11.05"

"Three Men in a Boat,1889,£12.87"

"The Time Machine,1895,£10.43"

"The Caves of Steel,1954,£8.12"

"Idle Thoughts of an Idle Fellow,1886,£7.32"

"A Christmas Carol,1843,£4.23"	

"A Tale of Two Cities,1859,£6.32"

"Great Expectations,1861,£13.21"

I have ensured that the program is future-proof by including an XML Loader for discounts. Therefore any new discounts that

are applied in the same way as current ones can be added by changing a single line in the Discount.xml file. I have

also ensure comprehensive JUnit testing of the project to ensure that the XML loader works as well as the actual

calculation function of the program.
