# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.
class Movie(models.Model):
    text = models.CharField(max_length=200)

def __unicode__(self):
        return self.text

def to_json(self):
        return {
            'id': self.id,
            'name': self.name
            }
