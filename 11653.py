N = int(input())

prime = 2
primes = []

while prime ** 2 <= N:
    while N % prime == 0:
        primes.append(prime)
        N = N // prime
    prime += 1

if N > 1: primes.append(N)

for i in primes:
    print(i)
