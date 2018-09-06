# -*- coding: utf-8 -*-
from __future__ import unicode_literals
from django.http import HttpResponse
from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status
from django.core.cache import cache

from .models import Movie

@api_view(['GET'])
def view_movies(request):
    f = open('movies.txt')
    line = f.readline()
    while line:
        print(line)
        line = f.readline()
        movie = Movie(text = line)
        movie.save()
    f.close()

    movies = Movie.objects.all()
    results = [movie.to_json() for movie in movies]
    return Response(results, status=status.HTTP_201_CREATED)

@api_view(['GET'])
def view_cached_movies(request):
    if 'movie' in cache:
        # get results from cache
        products = cache.get('movie')
        return Response(movies, status=status.HTTP_201_CREATED)

    else:
        movies = Movie.objects.all()
        results = [movie.to_json() for movie in movies]
        # store data in cache
        cache.set(movies, results, timeout=CACHE_TTL)
        return Response(results, status=status.HTTP_201_CREATED)
