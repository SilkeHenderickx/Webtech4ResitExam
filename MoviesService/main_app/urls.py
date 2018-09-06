from django.conf.urls import url
from .views import view_movies
from .views import view_cached_movies


urlpatterns = [
    url(r'^$', view_movies),
    url(r'^cache/', view_cached_movies),
]
