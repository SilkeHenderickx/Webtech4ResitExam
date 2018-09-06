from rest_framework import serializers
from .models import *

class MovieSerializer(serializers.Serializer):
    text = serializers.CharField(max_length=250)
