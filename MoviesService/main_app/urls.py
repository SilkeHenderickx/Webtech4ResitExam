from django.conf.urls import url
from .views import view_movies


urlpatterns = [
    url(r'^$', view_movies),
]
